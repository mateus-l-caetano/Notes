package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.data.AppDatabase
import com.example.notes.model.NoteModel
import com.example.notes.repository.NoteRepository
import com.example.notes.viewModel.NoteViewModel
import com.example.notes.viewModel.NoteViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NoteFragment : Fragment() {
    private lateinit var _binding: FragmentNoteBinding
    private val binding get() = _binding

    private val database by lazy { context?.let { AppDatabase.getDatabase(it) } }
    private val repository by lazy { database?.let { NoteRepository(it.noteDao()) } }

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        noteViewModel = repository?.let { NoteViewModelFactory(it).create(NoteViewModel::class.java) }!!

        binding.backButton.setOnClickListener { it.findNavController().popBackStack() }
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

        return view
    }
}