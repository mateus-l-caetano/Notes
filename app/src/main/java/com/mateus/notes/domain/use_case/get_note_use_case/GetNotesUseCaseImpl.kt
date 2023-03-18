package com.mateus.notes.domain.use_case.get_note_use_case

import android.util.Log
import com.mateus.notes.domain.model.Note
import com.mateus.notes.domain.repository.INoteRepository
import com.mateus.notes.utlis.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val noteRepository: INoteRepository
): IGetNotesUseCase {
    override suspend fun invoke(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            noteRepository.getAllNotes().collect{ notesList ->
                emit(Resource.Success(notesList))
            }
        } catch (e : Exception) {
            emit(
                Resource.Error(
                    e.message ?: "Erro ao buscar notas",
                    emptyList()
                )
            )
        }
    }
}