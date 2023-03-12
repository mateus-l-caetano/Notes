package com.mateus.notes.domain.repository

import com.mateus.notes.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    suspend fun getAllNotes() : Flow<List<NoteModel>>

    suspend fun insert(note: NoteModel) : Flow<Boolean>

    suspend fun updateNote(note: NoteModel) : Flow<Boolean>

    suspend fun delete(note: NoteModel): Flow<Boolean>

}