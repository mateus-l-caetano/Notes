package com.example.notes.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDAO: NoteDAO) {
    val allNotes: Flow<List<NoteModel>> = noteDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: NoteModel) {
        noteDAO.insert(note)
    }
}
