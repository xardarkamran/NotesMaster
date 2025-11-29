package com.navigation.live.presentation.ui.all_notes.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.domain.model.Note
import com.navigation.live.domain.use_cases.DeleteNoteUseCase
import com.navigation.live.domain.use_cases.GetNotesUseCase
import com.navigation.live.presentation.ui.shared.enum.SortType
import com.navigation.live.presentation.ui.all_notes.intent.NotesListIntent
import com.navigation.live.presentation.ui.all_notes.state.NotesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private var _uiStates = MutableStateFlow(NotesListUiState())
    val uiStates: StateFlow<NotesListUiState> = _uiStates.asStateFlow()
    private var _sortType = MutableStateFlow(SortType.LATEST)

    private var fetchAllNotesJob: Job? = null

    fun processIntent(intent: NotesListIntent) {
        when (intent) {
            is NotesListIntent.FetchAllNotes -> {
                fetchNotes()
            }

            is NotesListIntent.SortChanged -> {
                handleSortChanged(intent.sortType)
            }

            is NotesListIntent.DeleteNotes -> {
                handleDeleteNote(intent.note)
            }

            is NotesListIntent.ClearError -> {
                handleError()
            }
        }
    }

    private fun fetchNotes() {
        fetchAllNotesJob?.cancel()
        fetchAllNotesJob = viewModelScope.launch {
            _uiStates.update { it.copy(isLoading = true) }
            val notesFlow = getNotesUseCase()
            notesFlow.collect { notes ->
                val sortedNotes = sortedNote(notes, _sortType.value)
                _uiStates.update {
                    it.copy(
                        list = sortedNotes,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun handleSortChanged(sortType: SortType) {
        _sortType.value = sortType
        _uiStates.update { state ->
            val sortedState = sortedNote(state.list, sortType)
            state.copy(
                list = sortedState,
                sortType = sortType
            )
        }
    }

    private fun handleDeleteNote(note: Note) {
        viewModelScope.launch {
            try {
                deleteNoteUseCase(note)
                _uiStates.update { state ->
                    state.copy(error = null)
                }
            } catch (e: Exception) {
                _uiStates.update { state ->
                    state.copy(
                        error = e.message ?: "Error deleting note"
                    )
                }
            }
        }
    }

    private fun handleError() {
        _uiStates.update { state ->
            state.copy(
                error = null
            )
        }
    }

    private fun sortedNote(notes: List<Note>, sortType: SortType): List<Note> {
        return when (sortType) {
            SortType.LATEST -> notes.sortedByDescending { it.timestamp }
            SortType.OLDEST -> notes.sortedBy { it.timestamp }
            SortType.TITLE -> notes.sortedBy { it.title.lowercase() }
            SortType.COLOR -> notes.sortedBy { it.color }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchAllNotesJob?.cancel()
    }

}