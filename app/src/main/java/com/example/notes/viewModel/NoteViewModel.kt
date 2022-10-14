package com.example.notes.viewModel

import androidx.lifecycle.*
import com.example.notes.model.NoteModel
import com.example.notes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val allNotes: LiveData<List<NoteModel>> by lazy { noteRepository.allNotes.asLiveData() }

    fun insert(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun updateNote(notes: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(notes)
    }

    fun remove(position: Int) = viewModelScope.launch(Dispatchers.IO) {
        if(allNotes.value?.size!! > 0){
            val note = allNotes.value?.get(position)
            note?.let { noteRepository.delete(it) }
        }
    }

    fun addAt(position: Int, note: NoteModel) {

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