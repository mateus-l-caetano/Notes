package com.mateus.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mateus.notes.R
import com.mateus.notes.fragments.HomeScreenFragmentDirections
import com.mateus.notes.model.NoteModel

class NotesAdapter(private val dataSet: LiveData<List<NoteModel>>)
    : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.notesListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = "${dataSet.value?.get(position)?.title}"
        holder.view.setOnClickListener { view ->
            val note = dataSet.value?.get(position)?.let { NoteModel(it.uid, it.title, it.content) }
            val action = note?.let {
                HomeScreenFragmentDirections.actionHomeScreenFragmentToNoteFragment(
                    it
                )
            }
            action?.let { view.findNavController().navigate(it) }
        }
    }

    override fun getItemCount(): Int {
        if(dataSet.value.isNullOrEmpty())
            return 0
        else
            return dataSet.value!!.size
    }
}