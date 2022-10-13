package com.example.notes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class NoteModel (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="content") val content: String?
):Parcelable