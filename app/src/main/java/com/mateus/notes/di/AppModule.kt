package com.mateus.notes.di

import android.content.Context
import androidx.room.Room
import com.mateus.notes.data.datasource.AppDatabase
import com.mateus.notes.data.datasource.NoteDAO
import com.mateus.notes.data.repository.NoteRepositoryImpl
import com.mateus.notes.domain.use_case.get_note_use_case.GetNotesUseCaseImpl
import com.mateus.notes.domain.use_case.insert_note_use_case.InsertNoteUseCaseImpl
import com.mateus.notes.domain.repository.INoteRepository
import com.mateus.notes.domain.use_case.delete_note_use_case.DeleteNoteUseCaseImpl
import com.mateus.notes.domain.use_case.update_note_use_case.UpdateNoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }

    @Provides
    fun providesNoteDAO(
        appDatabase: AppDatabase
    ) : NoteDAO {
        return appDatabase.noteDao()
    }

    @Provides
    fun provideRepository(
        noteDAO: NoteDAO
    ) : INoteRepository {
        return NoteRepositoryImpl(noteDAO)
    }

    @Provides
    fun provideGetNotesUseCase(
        noteRepository: INoteRepository
    ) : GetNotesUseCaseImpl {
        return GetNotesUseCaseImpl(noteRepository)
    }

    @Provides
    fun provideInsertNoteUseCase(
        noteRepository: INoteRepository
    ) : InsertNoteUseCaseImpl {
        return InsertNoteUseCaseImpl(noteRepository)
    }

    @Provides
    fun provideUpdateNoteUseCase(
        noteRepository: INoteRepository
    ) : UpdateNoteUseCaseImpl {
        return UpdateNoteUseCaseImpl(noteRepository)
    }

    @Provides
    fun provideDeleteNoteUseCase(
        noteRepository: INoteRepository
    ) : DeleteNoteUseCaseImpl {
        return DeleteNoteUseCaseImpl(noteRepository)
    }
}