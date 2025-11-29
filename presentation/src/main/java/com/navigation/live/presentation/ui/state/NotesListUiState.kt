package com.navigation.live.presentation.ui.state

import com.navigation.live.domain.model.Note
import com.navigation.live.presentation.common.enum.SortType

data class NotesListUiState(
    val list: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val sortType: SortType = SortType.LATEST
)