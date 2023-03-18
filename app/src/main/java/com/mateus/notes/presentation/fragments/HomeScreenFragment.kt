package com.mateus.notes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mateus.notes.databinding.FragmentHomeScreenBinding
import com.mateus.notes.domain.model.Note
import com.mateus.notes.presentation.adapters.NotesAdapter
import com.mateus.notes.presentation.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesAdapter
    private val noteViewModel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        adapter = NotesAdapter(listOf())
        recyclerView = binding.homeScreenRecyclerView
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel.allNotes.observe(viewLifecycleOwner) {notes ->
            if (notes.isNotEmpty()){
                adapter = NotesAdapter(notes)
                recyclerView.adapter = adapter
                recyclerView.visibility = View.VISIBLE
                binding.emptyState.root.visibility = View.GONE
            } else {
                recyclerView.visibility = View.INVISIBLE
                binding.emptyState.root.visibility = View.VISIBLE
            }
        }

        swipeToDelete(recyclerView)

        binding.floatingActionButton.setOnClickListener {
            val note = Note(0, "", "")
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToNoteFragment(note)
            it.findNavController().navigate(action)
        }
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
                val deletedNote = noteViewModel.allNotes.value?.get(position)

                if (deletedNote != null) {
                    noteViewModel.remove(deletedNote)
                }
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}