package com.mateus.notes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mateus.notes.R
import com.mateus.notes.presentation.fragments.HomeScreenFragmentDirections
import com.mateus.notes.domain.model.Note

class NotesAdapter(private val dataSet: List<Note>)
    : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.notesListItemTitle)
        val content: TextView = view.findViewById(R.id.notesListItemContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = "${dataSet[position].title}"
        holder.content.text = "${dataSet[position].content}".also {
            if (it.length <= 50)
                it
            else
                it.substring(0..50)
        }

        holder.view.setOnClickListener { view ->
            val note = Note(dataSet[position].id, dataSet[position].title, dataSet[position].content)
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToNoteFragment(note)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = dataSet.size

}