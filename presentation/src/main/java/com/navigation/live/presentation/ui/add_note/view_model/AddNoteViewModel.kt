package com.navigation.live.presentation.ui.add_note.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.live.domain.model.Note
import com.navigation.live.domain.use_cases.AddNoteUseCase
import com.navigation.live.domain.use_cases.UpdateNoteUseCase
import com.navigation.live.presentation.ui.shared.utilz.ColorPalette
import com.navigation.live.presentation.ui.add_note.state.AddNoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: Int? = savedStateHandle.get<Int>("noteId")
    private val noteColor: Int? = savedStateHandle.get<Int>("noteColor")

    private var _uiState = MutableStateFlow(
        AddNoteUiState(
            selectedColor = noteColor ?: ColorPalette.color[0]
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(title = title, error = null)
        }
    }

    fun onDescriptionChange(des: String) {
        _uiState.update {
            it.copy(content = des, error = null)
        }
    }

    fun onColorChange(color: Int) {
        _uiState.update {
            it.copy(selectedColor = color)
        }
    }

    fun saveNote() {
        val currentState = _uiState.value
        if (currentState.title.isBlank() || currentState.content.isBlank()) {
            _uiState.update {
                it.copy(error = "Title and content could not be empty")
            }
            return
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            try {
                val note = Note(
                    id = noteId,
                    title = currentState.title.trim(),
                    content = currentState.content.trim(),
                    timestamp = currentState.timeStamp,
                    color = currentState.selectedColor
                )

                if (noteId == null) {
                    addNoteUseCase(note)
                } else {
                    updateNoteUseCase(note)
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isNoteSaved = true
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Error saving note"
                    )
                }
            }
        }
    }

    fun resetSavedState() {
        _uiState.update {
            it.copy(
                isNoteSaved = false
            )
        }
    }


}