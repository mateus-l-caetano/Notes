package com.example.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="content") val content: String?
)