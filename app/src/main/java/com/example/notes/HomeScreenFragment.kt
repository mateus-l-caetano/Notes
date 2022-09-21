package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.FragmentHomeScreenBinding
import com.example.notes.model.AppDatabase
import com.example.notes.model.NoteRepository
import com.example.notes.viewModel.NoteViewModel
import com.example.notes.viewModel.NoteViewModelFactory
import com.google.android.material.snackbar.Snackbar
import layout.NotesAdapter

class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val database by lazy { context?.let { AppDatabase.getDatabase(it) } }
    private val repository by lazy { database?.let { NoteRepository(it.noteDao()) } }

    private lateinit var adapter: NotesAdapter
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        noteViewModel = repository?.let { NoteViewModelFactory(it).create(NoteViewModel::class.java) }!!
        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer {
            adapter = NotesAdapter(noteViewModel.allNotes)
        })
        val recyclerView = binding.homeScreenRecyclerView
        recyclerView.adapter = NotesAdapter(noteViewModel.allNotes)

        swipeToDelete(recyclerView)

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_noteFragment)
        }

        return view
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedCourse = noteViewModel.allNotes.value?.get(position)

                noteViewModel.removeAt(position)
                adapter = NotesAdapter(noteViewModel.allNotes)
                recyclerView.adapter = adapter
                adapter.notifyItemRemoved(position)

                Snackbar.make(recyclerView, "Deleted " + deletedCourse, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            deletedCourse?.let { note -> noteViewModel.addAt(position, note) }
                            adapter = NotesAdapter(noteViewModel.allNotes)
                            recyclerView.adapter = adapter
                            adapter.notifyItemInserted(position)
                        }).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}