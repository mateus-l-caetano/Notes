package com.mateus.notes.domain.use_case.delete_note_use_case

import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.repository.INoteRepository
import com.mateus.notes.utlis.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val noteRepository: INoteRepository
) : IDeleteNoteUseCase {
    override fun invoke(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            noteRepository.removeNote(note)
        } catch (e : Exception) {
            emit(
                Resource.Error(
                    e.message ?: "Erro ao deletar nota",
                    false
                )
            )
        } finally {
            emit(Resource.Success(true))
        }
    }
}