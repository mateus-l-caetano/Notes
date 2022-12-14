package com.mateus.notes.data

import androidx.room.*
import com.mateus.notes.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM noteModel")
    fun getAll(): Flow<List<NoteModel>>

    @Query("SELECT * FROM noteModel WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<NoteModel>>

    @Query("SELECT * FROM noteModel WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): NoteModel

    @Insert
    suspend fun insert(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    suspend fun delete(note: NoteModel)
}