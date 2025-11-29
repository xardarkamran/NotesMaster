package com.navigation.live.presentation.ui.add_note.state

import com.navigation.live.presentation.ui.shared.utilz.ColorPalette

data class AddNoteUiState(
    val title: String = "",
    val content: String = "",
    val selectedColor: Int = ColorPalette.color[0],
    val timeStamp: Long = System.currentTimeMillis(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isNoteSaved: Boolean = false
)
