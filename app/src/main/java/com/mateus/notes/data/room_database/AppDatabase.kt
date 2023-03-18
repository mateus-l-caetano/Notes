package com.mateus.notes.data.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateus.notes.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDAO
}