package com.mateus.notes.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.use_case.delete_note_use_case.IDeleteNoteUseCase
import com.mateus.notes.domain.use_case.get_note_use_case.IGetNotesUseCase
import com.mateus.notes.domain.use_case.insert_note_use_case.IInsertNoteUseCase
import com.mateus.notes.domain.use_case.update_note_use_case.IUpdateNoteUseCase
import com.mateus.notes.utlis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase : IGetNotesUseCase,
    private val insertNoteUseCase : IInsertNoteUseCase,
    private val updateNoteUseCase: IUpdateNoteUseCase,
    private val deleteNoteUseCase: IDeleteNoteUseCase
) : ViewModel() {
    private val _allNotes: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    val allNotes: LiveData<List<Note>> get() = _allNotes

    private val _state : MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData<Resource<Boolean>>()
    }
    val state : LiveData<Resource<Boolean>> get() = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesUseCase().collect { notes ->
                when(notes){
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Success -> {
                        _allNotes.postValue(notes.data.orEmpty())
                    }
                }
            }
        }
    }

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase(note).collect {
                _state.postValue(it)
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase(note).collect {
                _state.postValue(it)
            }
        }
    }

    fun remove(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase(note).collect {
                _state.postValue(it)
            }
        }
    }
}