package com.mateus.notes.domain.use_case.get_note_use_case

import com.mateus.notes.data.model.toNote
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.repository.INoteRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val noteRepository: INoteRepository
): IGetNotesUseCase {
    override fun invoke(): Flow<List<Note>> = flow {
        noteRepository.getAllNotes().collect { noteModelList ->
            val notesList = noteModelList.map { noteModel ->
                noteModel.toNote()
            }

            emit(notesList)
        }
    }
}