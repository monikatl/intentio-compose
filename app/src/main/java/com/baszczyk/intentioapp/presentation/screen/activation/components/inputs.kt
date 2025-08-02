package com.baszczyk.intentioapp.presentation.screen.activation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton
import com.baszczyk.intentioapp.presentation.components.OutlinedTextInput

@Composable
fun ActivationInputWithButton(
    header: String,
    label: String,
    onSaveClick: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    Column {
        Text(
            text = header
        )

        OutlinedTextInput(
            label = label,
            text = name.value
        ) { name.value = it }

        BasicOutlinedButton(
            label = "DALEJ",
            onClick = onSaveClick
        )
    }
}