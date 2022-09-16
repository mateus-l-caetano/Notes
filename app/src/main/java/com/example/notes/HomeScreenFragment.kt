package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.FragmentHomeScreenBinding
import com.google.android.material.snackbar.Snackbar
import layout.NotesAdapter

class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: NotesAdapter
    val dataSet: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        dataSet.addAll(mutableListOf("teste1", "teste2", "teste3", "teste4", "teste5"))
        adapter = NotesAdapter(dataSet.toTypedArray())
        val recyclerView = binding.homeScreenRecyclerView
        recyclerView.adapter = adapter

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
                val deletedCourse = dataSet.get(position)

                dataSet.removeAt(position)
                adapter = NotesAdapter(dataSet.toTypedArray())
                recyclerView.adapter = adapter
                adapter.notifyItemRemoved(position)

                Snackbar.make(recyclerView, "Deleted " + deletedCourse, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            dataSet.add(position, deletedCourse)
                            adapter = NotesAdapter(dataSet.toTypedArray())
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