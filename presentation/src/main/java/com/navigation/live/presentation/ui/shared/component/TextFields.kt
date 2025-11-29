package com.navigation.live.presentation.ui.shared.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TitleTextField(
    title: String,
    isError: Boolean = false,
    onTitleChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        placeholder = { Text("Title") },
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            errorIndicatorColor = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
fun DescriptionTextField(
    title: String,
    isError: Boolean = false,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        onValueChange = onDescriptionChange,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp),
        placeholder = {
            Text("Description")
        },
        isError = isError,
        maxLines = Int.MAX_VALUE,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            errorIndicatorColor = MaterialTheme.colorScheme.error
        )
    )
}