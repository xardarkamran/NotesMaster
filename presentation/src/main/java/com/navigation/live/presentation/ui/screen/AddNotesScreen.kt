package com.navigation.live.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.navigation.live.presentation.ui.component.DescriptionTextField
import com.navigation.live.presentation.ui.component.TitleTextField
import com.navigation.live.presentation.ui.viewmodel.AddNoteViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesScreen(
    onNavigationBack: () -> Unit = {},
    noteViewModel: AddNoteViewModel = hiltViewModel()
) {
    val uiState by noteViewModel.uiState.collectAsState()

    // Handle navigation back after saving
    LaunchedEffect(uiState.isNoteSaved) {
        if (uiState.isNoteSaved) {
            delay(150)
            noteViewModel.resetSavedState()
            onNavigationBack()
        }
    }

    //show error screen
    LaunchedEffect(uiState.error) {
        uiState.error?.let {

        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Note"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onNavigationBack
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back icon"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = noteViewModel::saveNote,
                        enabled = !uiState.isLoading
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "save data"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TitleTextField(title = uiState.title) {
                noteViewModel.onTitleChange(it)
            }
            Spacer(modifier = Modifier.height(5.dp))
            DescriptionTextField(title = uiState.content) {
                noteViewModel.onDescriptionChange(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Color Picker
            Text(
                text = "Select Color",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

        }
    }
}


@Preview
@Composable
fun PreviewAddNotesScreen() {
    AddNotesScreen()
}