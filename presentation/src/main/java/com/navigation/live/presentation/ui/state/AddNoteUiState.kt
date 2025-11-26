package com.navigation.live.presentation.ui.state

data class AddNoteUiState(
    val title: String = "",
    val content: String = "",
    val selectedColor: Int = ColorPalette.color[0],
    val timeStamp: Long = System.currentTimeMillis(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isNoteSaved: Boolean = false
)

object ColorPalette {
    val color = listOf(
        0xFFE3F2FD.toInt(), // Light Blue
        0xFFF3E5F5.toInt(), // Light Purple
        0xFFE8F5E9.toInt(), // Light Green
        0xFFFFF3E0.toInt(), // Light Orange
        0xFFFCE4EC.toInt()  // Light Pink
    )
}