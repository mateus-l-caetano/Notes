package com.example.notes.viewModel

import androidx.lifecycle.*
import com.example.notes.model.NoteModel
import com.example.notes.model.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val allNotes: LiveData<List<NoteModel>> = noteRepository.allNotes.asLiveData()

    fun insert(note: NoteModel) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun removeAt(position: Int) {

    }

    fun addAt(position: Int, note: NoteModel){

    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}