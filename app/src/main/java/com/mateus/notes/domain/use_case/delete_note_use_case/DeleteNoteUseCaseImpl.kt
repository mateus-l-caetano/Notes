package com.mateus.notes.domain.use_case.delete_note_use_case

import android.util.Log
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.model.toNoteModel
import com.mateus.notes.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val noteRepository: INoteRepository
) : IDeleteNoteUseCase {
    override fun invoke(note: Note) : Flow<Boolean> = flow {
        Log.d("nota", "chamou delete use case")
        noteRepository.delete(note.toNoteModel())
            .catch {
                Log.d("nota", "deu ruim delete use case")
                emit(false)
            }
            .collect {
                Log.d("nota", "delete use case")
                emit(it)
            }
    }
}