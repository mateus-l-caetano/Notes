package com.mateus.notes.domain.use_case.get_note_use_case

import com.mateus.notes.domain.model.Note
import com.mateus.notes.utlis.Resource
import kotlinx.coroutines.flow.Flow

interface IGetNotesUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Note>>>
}