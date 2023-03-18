package com.mateus.notes.domain.use_case.delete_note_use_case

import com.mateus.notes.domain.model.Note
import com.mateus.notes.utlis.Resource
import kotlinx.coroutines.flow.Flow

interface IDeleteNoteUseCase {
    operator fun invoke(note: Note): Flow<Resource<Boolean>>
}