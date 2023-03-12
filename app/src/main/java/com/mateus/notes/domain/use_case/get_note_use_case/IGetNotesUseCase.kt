package com.mateus.notes.domain.use_case.get_note_use_case

import com.mateus.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface IGetNotesUseCase {
    operator fun invoke(): Flow<List<Note>>
}