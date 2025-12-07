package com.navigation.live.note.master.di

import com.navigation.live.domain.repository.NoteRepository
import com.navigation.live.domain.use_cases.AddNoteUseCase
import com.navigation.live.domain.use_cases.DeleteNoteUseCase
import com.navigation.live.domain.use_cases.GetNoteByIdUseCase
import com.navigation.live.domain.use_cases.GetNotesUseCase
import com.navigation.live.domain.use_cases.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module for providing use case dependencies.
 * Use cases are provided here since they are used by ViewModels in the presentation layer.
 * 
*/
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddNoteUseCase(repository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    @Provides
    fun provideUpdateNoteUseCase(repository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    fun provideGetNoteByIdUseCase(repository: NoteRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCase(repository)
    }
}


