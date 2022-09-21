package com.example.notes.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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
    fun insert(note: NoteModel)

    @Delete
    fun delete(note: NoteModel)
}