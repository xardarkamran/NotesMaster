package com.navigation.live.domain.use_cases

import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id:Int): Note? {
        return noteRepository.getNoteById(id)
    }
}