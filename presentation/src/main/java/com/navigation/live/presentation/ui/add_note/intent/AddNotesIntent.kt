package com.navigation.live.presentation.ui.add_note.intent

sealed class AddNotesIntent {
    data class OnTitleChanged(var title: String) : AddNotesIntent()
    data class OnDescriptionChanged(var des: String) : AddNotesIntent()
    data class OnColorChanged(val color: Int) : AddNotesIntent()
    data object NotesSaved : AddNotesIntent()
    data object ResetSavedState : AddNotesIntent()
}