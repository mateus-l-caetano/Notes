package com.mateus.notes.domain.use_case.insert_note_use_case

import android.util.Log
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.repository.INoteRepository
import com.mateus.notes.utlis.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertNoteUseCaseImpl @Inject constructor(
    private val noteRepository : INoteRepository
) : IInsertNoteUseCase {
    override fun invoke(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            noteRepository.addNote(note)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Erro ao inserir",
                    false
                )
            )
        }
    }
}