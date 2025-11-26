package com.navigation.live.domain.repository

import com.navigation.live.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Note operations.
 * This defines the contract for data operations in the domain layer.
 */
interface NoteRepository {
    /**
     * Insert a new note into the database.
     */
    suspend fun insertNote(note: Note)

    /**
     * Delete a note from the database.
     */
    suspend fun deleteNote(note: Note)

    /**
     * Update an existing note in the database.
     */
    suspend fun updateNote(note: Note)

    /**
     * Get all notes as a Flow.
     * Returns a Flow that emits the list of notes whenever it changes.
     */
    fun getNotes(): Flow<List<Note>>

    /**
     * Get a note by its ID.
     */
    suspend fun getNoteById(id: Int): Note?
}

