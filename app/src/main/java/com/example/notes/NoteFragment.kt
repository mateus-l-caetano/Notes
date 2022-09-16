package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.notes.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private lateinit var _binding: FragmentNoteBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.backButton.setOnClickListener { it.findNavController().popBackStack() }

        return view
    }
}