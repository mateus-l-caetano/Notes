package com.mateus.notes.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.use_case.delete_note_use_case.IDeleteNoteUseCase
import com.mateus.notes.domain.use_case.get_note_use_case.IGetNotesUseCase
import com.mateus.notes.domain.use_case.insert_note_use_case.IInsertNoteUseCase
import com.mateus.notes.domain.use_case.update_note_use_case.IUpdateNoteUseCase
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

    private val _insert: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }
    val insert: LiveData<Boolean> get() = _insert

    private val _update: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }
    val update: LiveData<Boolean> get() = _update

    private val _delete: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesUseCase().collect {
                _allNotes.postValue(it)
            }
        }
    }

    fun insert(note: Note) {
        _insert.value = false
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase(note).collect {
                _insert.postValue(it)
            }
        }
    }

    fun updateNote(note: Note) {
        _update.value = false
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase(note).collect {
                _update.postValue(it)
            }
        }
    }

    fun remove(note: Note) {
        Log.d("nota", "delete viewModel")
        _delete.value = false
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase(note).collect {
                _delete.postValue(it)
            }
        }
    }
}