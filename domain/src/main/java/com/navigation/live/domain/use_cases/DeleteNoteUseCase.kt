package com.navigation.live.domain.use_cases

import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}