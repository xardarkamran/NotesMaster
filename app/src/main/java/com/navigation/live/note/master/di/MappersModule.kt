package com.navigation.live.note.master.di

import com.navigation.live.data.local.entity.NoteEntity
import com.navigation.live.data.mappers.NoteMapperImpl
import com.navigation.live.data.mappers.Mapper
import com.navigation.live.domain.model.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideNoteMapper(): Mapper<NoteEntity, Note> = NoteMapperImpl()

}