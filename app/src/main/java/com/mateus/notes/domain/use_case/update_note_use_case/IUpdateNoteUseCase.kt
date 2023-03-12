package com.mateus.notes.domain.use_case.update_note_use_case

import com.mateus.notes.domain.model.Note

interface IUpdateNoteUseCase {
    operator fun invoke(note : Note): kotlinx.coroutines.flow.Flow<Boolean>
}