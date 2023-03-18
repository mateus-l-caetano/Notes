package com.mateus.notes.domain.repository

import com.mateus.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    suspend fun getAllNotes() : Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun removeNote(note: Note)
}