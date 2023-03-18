package com.mateus.notes.data.repository

import android.util.Log
import com.mateus.notes.data.datasource.INotesLocalDataSource
import com.mateus.notes.data.room_database.NoteDAO
import com.mateus.notes.data.model.NoteModel
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDataSource: INotesLocalDataSource
) : INoteRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> {
        Log.d("nota", "get repository")
        return noteDataSource.getNotes()
    }

    override suspend fun addNote(note: Note) {
        return noteDataSource.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        return noteDataSource.updateNote(note)
    }

    override suspend fun removeNote(note: Note) {
        return noteDataSource.removeNote(note)
    }
}
