package com.navigation.live.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.navigation.live.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM Notes ORDER BY timestamp DESC")
    fun getAllNotes():Flow<List<NoteEntity>>

    @Query("SELECT * FROM Notes WHERE id = :id")
    fun getNoteById(id:Int):NoteEntity

    @Query("SELECT * FROM Notes ORDER BY timestamp ASC")
    fun getNotesOldestFirst():Flow<List<NoteEntity>>

    @Query("SELECT * FROM Notes ORDER BY title ASC")
    fun getNotesByTitle():Flow<List<NoteEntity>>

    @Query("SELECT * FROM Notes ORDER BY color ASC")
    fun getNotesByColor():Flow<List<NoteEntity>>

}