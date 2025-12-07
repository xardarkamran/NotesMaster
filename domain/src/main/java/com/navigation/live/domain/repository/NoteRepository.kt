package com.navigation.live.domain.repository

import com.navigation.live.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
}

