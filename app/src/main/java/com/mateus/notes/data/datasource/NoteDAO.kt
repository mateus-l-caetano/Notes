package com.mateus.notes.data.datasource

import androidx.room.*
import com.mateus.notes.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM NoteModel")
    fun getAll(): Flow<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun delete(note: NoteModel)
}