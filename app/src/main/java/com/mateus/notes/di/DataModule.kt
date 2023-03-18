package com.mateus.notes.di

import android.content.Context
import androidx.room.Room
import com.mateus.notes.data.datasource.INotesLocalDataSource
import com.mateus.notes.data.datasource.NotesRoomDataSource
import com.mateus.notes.data.room_database.AppDatabase
import com.mateus.notes.data.room_database.NoteDAO
import com.mateus.notes.data.repository.NoteRepositoryImpl
import com.mateus.notes.domain.repository.INoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }

    @Provides
    fun provideNoteDAO(
        appDatabase: AppDatabase
    ) : NoteDAO {
        return appDatabase.noteDao()
    }

    @Provides
    fun provideNotesLocalDataSource(
        noteDAO: NoteDAO
    ) : INotesLocalDataSource {
        return NotesRoomDataSource(noteDAO)
    }

    @Provides
    fun provideRepository(
        notesLocalDataSource: INotesLocalDataSource
    ) : INoteRepository {
        return NoteRepositoryImpl(notesLocalDataSource)
    }
}