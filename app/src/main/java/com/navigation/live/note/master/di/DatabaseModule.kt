package com.navigation.live.note.master.di

import android.content.Context
import androidx.room.Room
import com.navigation.live.data.local.dao.NotesDao
import com.navigation.live.data.local.database.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseModule(@ApplicationContext context: Context): NotesDataBase {
        return Room.databaseBuilder(
            context = context,
            NotesDataBase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration() // For development - remove in production
            .build()
    }

    @Provides
    @Singleton
    fun providesNoteDao(notesDataBase: NotesDataBase): NotesDao {
        return notesDataBase.notesDao()
    }
}


