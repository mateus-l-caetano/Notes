package com.mateus.notes.di

import com.mateus.notes.data.repository.NoteRepositoryImpl
import com.mateus.notes.domain.repository.INoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ) : INoteRepository
}