package com.mateus.notes.di

import com.mateus.notes.domain.use_case.delete_note_use_case.DeleteNoteUseCaseImpl
import com.mateus.notes.domain.use_case.delete_note_use_case.IDeleteNoteUseCase
import com.mateus.notes.domain.use_case.get_note_use_case.GetNotesUseCaseImpl
import com.mateus.notes.domain.use_case.get_note_use_case.IGetNotesUseCase
import com.mateus.notes.domain.use_case.insert_note_use_case.IInsertNoteUseCase
import com.mateus.notes.domain.use_case.insert_note_use_case.InsertNoteUseCaseImpl
import com.mateus.notes.domain.use_case.update_note_use_case.IUpdateNoteUseCase
import com.mateus.notes.domain.use_case.update_note_use_case.UpdateNoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetNotesUseCase(
        getNotesUseCaseImpl: GetNotesUseCaseImpl
    ) : IGetNotesUseCase

    @Binds
    fun bindInsertNoteUseCase(
        insertNoteUseCaseImpl: InsertNoteUseCaseImpl
    ) : IInsertNoteUseCase

    @Binds
    fun bindUpdateNoteUseCase(
        updateNoteUseCaseImpl: UpdateNoteUseCaseImpl
    ) : IUpdateNoteUseCase

    @Binds
    fun bindDeleteNoteUseCase(
        deleteNoteUseCaseImpl: DeleteNoteUseCaseImpl
    ) : IDeleteNoteUseCase
}