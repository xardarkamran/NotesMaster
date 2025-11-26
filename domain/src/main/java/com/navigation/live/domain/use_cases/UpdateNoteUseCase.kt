package com.navigation.live.domain.use_cases

import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.isValid())
            repository.updateNote(note)
        else
            throw IllegalArgumentException("Note title and content cannot be empty")
    }
}