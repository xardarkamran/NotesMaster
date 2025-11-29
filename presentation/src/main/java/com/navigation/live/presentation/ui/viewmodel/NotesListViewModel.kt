package com.navigation.live.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.domain.model.Note
import com.navigation.live.domain.use_cases.DeleteNoteUseCase
import com.navigation.live.domain.use_cases.GetNotesUseCase
import com.navigation.live.presentation.common.enum.SortType
import com.navigation.live.presentation.ui.state.NotesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        Log.d("NotesViewModel", "init")
        observeNotes()
    }

    private fun observeNotes() {
        Log.d("NotesViewModel", "observeNotes: start")
        viewModelScope.launch {
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

    fun onSortTypeChange(sortType: SortType) {
        _sortType.value = sortType
        _uiStates.update { currentState ->
            val sortedNotes = sortedNote(currentState.list, sortType)
            currentState.copy(
                list = sortedNotes,
                sortType = sortType
            )
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                deleteNoteUseCase(note = note)
                _uiStates.update { it.copy(error = null) }
            } catch (e: Exception) {
                _uiStates.update { it.copy(error = e.message ?: "Error deleting note") }
            }


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

}