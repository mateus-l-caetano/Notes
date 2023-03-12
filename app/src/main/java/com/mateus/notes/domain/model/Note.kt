package com.mateus.notes.domain.model

import android.os.Parcelable
import com.mateus.notes.data.model.NoteModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note (
    val id: Int,
    val title: String?,
    val content: String?
): Parcelable

fun Note.toNoteModel() = NoteModel(
    id, title, content
)