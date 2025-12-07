package com.navigation.live.presentation.ui.add_note.intent

sealed class AddNotesIntent {
    data object LoadNote :AddNotesIntent()
    data class OnTitleChanged(val title: String) : AddNotesIntent()
    data class OnDescriptionChanged(val des: String) : AddNotesIntent()
    data class OnColorChanged(val color: Int) : AddNotesIntent()
    data object NotesSaved : AddNotesIntent()
    data object ResetSavedState : AddNotesIntent()
}