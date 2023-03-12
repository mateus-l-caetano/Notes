package com.mateus.notes.data.repository

import android.util.Log
import com.mateus.notes.data.datasource.NoteDAO
import com.mateus.notes.data.model.NoteModel
import com.mateus.notes.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDAO: NoteDAO
) : INoteRepository {
    override suspend fun getAllNotes(): Flow<List<NoteModel>> {
        return try {
            noteDAO.getAll()
        } catch (e:java.lang.Exception) {
            flow { emit(listOf()) }
        }
    }

    override suspend fun insert(note: NoteModel) : Flow<Boolean> {
        return try {
            noteDAO.insert(note)
            Log.d("nota", "delete repository")
            flow { emit(true) }
        } catch (e:java.lang.Exception){
            Log.d("nota", "deu ruim delete repository $e")
            flow{ emit(false) }
        }
    }

    override suspend fun updateNote(note: NoteModel) : Flow<Boolean> {
        return try {
            noteDAO.updateNote(note)
            flow { emit(true) }
        } catch (e:java.lang.Exception){
            flow{ emit(false) }
        }
    }

    override suspend fun delete(note: NoteModel) : Flow<Boolean> {
        return try {
            noteDAO.delete(note)
            flow { emit(true) }
        } catch (e: java.lang.Exception) {
            flow { emit(false) }
        }
    }

//    fun loadAllByIds(userIds: IntArray): Flow<List<NoteModel>> {
//        return noteDAO.loadAllByIds(userIds)
//    }
//
//    fun findByTitle(title: String): NoteModel {
//        return noteDAO.findByTitle(title)
//    }
}
