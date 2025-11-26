package com.navigation.live.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.navigation.live.data.local.dao.NotesDao
import com.navigation.live.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}