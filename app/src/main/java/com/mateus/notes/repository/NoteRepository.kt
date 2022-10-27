package com.mateus.notes.repository

import androidx.annotation.WorkerThread
import com.mateus.notes.data.NoteDAO
import com.mateus.notes.model.NoteModel
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDAO: NoteDAO) {
    val allNotes: Flow<List<NoteModel>> = noteDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: NoteModel) {
        noteDAO.insert(note)
    }

    fun loadAllByIds(userIds: IntArray): Flow<List<NoteModel>> {
        return noteDAO.loadAllByIds(userIds)
    }

    fun findByTitle(title: String): NoteModel {
        return noteDAO.findByTitle(title)
    }

    fun updateNote(notes: NoteModel) {
        return noteDAO.updateNote(notes)
    }

    suspend fun delete(note: NoteModel) {
        return noteDAO.delete(note)
    }
}
