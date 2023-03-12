package com.mateus.notes.domain.use_case.delete_note_use_case

import com.mateus.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface IDeleteNoteUseCase {
    operator fun invoke(note: Note): Flow<Boolean>
}