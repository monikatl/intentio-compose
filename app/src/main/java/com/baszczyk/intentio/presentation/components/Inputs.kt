package com.baszczyk.intentioapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OutlinedTextInput(
    label: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label
            )
        },
        singleLine = true
    )
}