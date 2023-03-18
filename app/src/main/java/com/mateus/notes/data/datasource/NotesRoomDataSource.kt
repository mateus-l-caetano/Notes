package com.mateus.notes.data.datasource

import android.util.Log
import com.mateus.notes.data.model.toNote
import com.mateus.notes.data.room_database.NoteDAO
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.model.toNoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRoomDataSource @Inject constructor(
    private val database : NoteDAO
) : INotesLocalDataSource {
    override suspend fun getNotes(): Flow<List<Note>> {
        return database.getAll().map { list -> list.map { noteModel -> noteModel.toNote() } }
    }

    override suspend fun addNote(note: Note) {
        return database.insert(note.toNoteModel())
    }

    override suspend fun removeNote(note: Note) {
        return database.delete(note.toNoteModel())
    }

    override suspend fun updateNote(note: Note) {
        return database.updateNote(note.toNoteModel())
    }
}