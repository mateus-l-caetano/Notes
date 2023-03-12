package com.mateus.notes.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mateus.notes.databinding.FragmentNoteBinding
import com.mateus.notes.domain.model.Note
import com.mateus.notes.presentation.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()
    private val args by navArgs<NoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = args.note
        if(note.title?.isNotBlank() == true) {
            setTitleAndDescription(note)
            updateNoteWhenSaveButtonClicked(view)
        }
        else {
            setSaveButtonClick(view)
        }

        binding.backButton.setOnClickListener { it.findNavController().popBackStack() }
    }

    private fun setSaveButtonClick(view: View) {
        binding.saveButton.setOnClickListener {
            noteViewModel.insert(
                Note(
                    id = 0,
                    title = binding.noteScreenTitle.text.toString(),
                    content = binding.noteScreenDescription.text.toString()
                )
            )

            noteViewModel.insert.observe(viewLifecycleOwner) {
                if(!it){
                    binding.indicator.visibility = View.VISIBLE
                } else {
                    binding.indicator.visibility = View.INVISIBLE
                    Snackbar.make(view, "Nota adicionada", Snackbar.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun updateNoteWhenSaveButtonClicked(view: View) {
        binding.saveButton.setOnClickListener {
            noteViewModel.updateNote(
                Note(
                    id = args.note.id,
                    title = binding.noteScreenTitle.text.toString(),
                    content = binding.noteScreenDescription.text.toString()
                )
            )

            Snackbar.make(view, "Nota atualizada", Snackbar.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun setTitleAndDescription(note: Note) {
        binding.noteScreenTitle.text = Editable.Factory.getInstance().newEditable(note.title)
        binding.noteScreenDescription.text =
            Editable.Factory.getInstance().newEditable(note.content)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}