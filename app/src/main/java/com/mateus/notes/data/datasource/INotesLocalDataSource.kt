package com.mateus.notes.data.datasource

import com.mateus.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INotesLocalDataSource {
    suspend fun getNotes() : Flow<List<Note>>

    suspend fun addNote(note : Note)

    suspend fun removeNote(note : Note)

    suspend fun updateNote(note : Note)
}