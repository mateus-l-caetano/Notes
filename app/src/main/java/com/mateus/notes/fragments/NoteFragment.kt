package com.mateus.notes.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mateus.notes.databinding.FragmentNoteBinding
import com.mateus.notes.data.AppDatabase
import com.mateus.notes.model.NoteModel
import com.mateus.notes.repository.NoteRepository
import com.mateus.notes.viewModel.NoteViewModel
import com.mateus.notes.viewModel.NoteViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NoteFragment : Fragment() {
    private lateinit var _binding: FragmentNoteBinding
    private val binding get() = _binding

    private val database by lazy { context?.let { AppDatabase.getDatabase(it) } }
    private val repository by lazy { database?.let { NoteRepository(it.noteDao()) } }

    private lateinit var noteViewModel: NoteViewModel
    private val args by navArgs<NoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        noteViewModel = repository?.let { NoteViewModelFactory(it).create(NoteViewModel::class.java) }!!

        val note = args.note
        if(note.title?.isNotBlank() == true) {
            binding.noteScreenTitle.text = Editable.Factory.getInstance().newEditable(note.title) //note.title as Editable
            binding.noteScreenDescription.text = Editable.Factory.getInstance().newEditable(note.content) //note.content as Editable

            binding.saveButton.setOnClickListener {
                noteViewModel.updateNote(
                    NoteModel(
                        uid = note.uid,
                        title = binding.noteScreenTitle.text.toString(),
                        content = binding.noteScreenDescription.text.toString()
                    )
                )

                Snackbar.make(view, "Nota atualizada", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
        else {
            binding.saveButton.setOnClickListener {
                noteViewModel.insert(
                    NoteModel(
                        uid = 0,
                        title = binding.noteScreenTitle.text.toString(),
                        content = binding.noteScreenDescription.text.toString()
                    )
                )

                Snackbar.make(view, "Nota adicionada", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        binding.backButton.setOnClickListener { it.findNavController().popBackStack() }

        return view
    }
}