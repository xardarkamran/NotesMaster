package com.navigation.live.domain.use_cases

import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository

class AddNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.isValid()) {
            noteRepository.insertNote(note)
        } else {
            throw IllegalArgumentException("Note title and content be empty")
        }
    }

}