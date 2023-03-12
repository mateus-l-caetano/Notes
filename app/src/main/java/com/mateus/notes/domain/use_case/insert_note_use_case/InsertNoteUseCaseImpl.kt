package com.mateus.notes.domain.use_case.insert_note_use_case

import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.model.toNoteModel
import com.mateus.notes.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertNoteUseCaseImpl @Inject constructor(
    private val noteRepository : INoteRepository
) : IInsertNoteUseCase {
    override fun invoke(note: Note): Flow<Boolean> = flow {
        noteRepository.insert(note.toNoteModel())
            .catch {
                emit(false)
            }
            .collect{
                emit(it)
            }
    }
}