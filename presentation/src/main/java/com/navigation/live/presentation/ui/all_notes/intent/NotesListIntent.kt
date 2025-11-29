package com.navigation.live.presentation.ui.all_notes.intent

import com.navigation.live.domain.model.Note
import com.navigation.live.presentation.ui.shared.enum.SortType

sealed class NotesListIntent {
    data object FetchAllNotes : NotesListIntent()
    data class SortChanged(val sortType: SortType) : NotesListIntent()
    data class DeleteNotes(val note: Note) : NotesListIntent()
    data object ClearError : NotesListIntent()
}