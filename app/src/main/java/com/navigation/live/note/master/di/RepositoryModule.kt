package com.navigation.live.note.master.di

import com.navigation.live.data.local.dao.NotesDao
import com.navigation.live.data.local.entity.NoteEntity
import com.navigation.live.data.repository.NotesRepositoryImpl
import com.navigation.live.data.mappers.Mapper
import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        notesDao: NotesDao,
        noteMapper: Mapper<NoteEntity, Note>
    ): NoteRepository {
        return NotesRepositoryImpl(
            notesDao = notesDao,
            noteMapper = noteMapper
        )
    }

}


