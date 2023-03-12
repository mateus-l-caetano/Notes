package com.mateus.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateus.notes.domain.model.Note

@Entity
data class NoteModel (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="content") val content: String?
)

fun NoteModel.toNote() : Note = Note(
    id = uid,
    title = title,
    content = content
)