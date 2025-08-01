package com.baszczyk.intentioapp.presentation.screen.settings.groups.parish.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.baszczyk.intentioapp.domain.model.MassDay
import com.baszczyk.intentioapp.domain.model.MassPattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMassDialog(
    dialogTitle: String,
    massDay: MassDay,
    onDismissRequest: () -> Unit,
    onConfirmation: (MassPattern) -> Unit
) {
    val pickerState = rememberTimePickerState(12,0)

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            TimePicker(
                state = pickerState
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val massPattern = MassPattern("${pickerState.hour}:${pickerState.minute}", massDay)
                    onConfirmation(massPattern)
                }
            ) {
                Text("Dodaj")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Anuluj")
            }
        }
    )
}

@Composable
fun EditParishDataDialog(
    dialogTitle: String,
    currentName: String,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit
) {
    var text by remember { mutableStateOf(currentName) }

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it }
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(text)
                    onDismissRequest()
                }
            ) {
                Text("Zapisz")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Anuluj")
            }
        }
    )
}

data class EditDialogElements(
    val label: String,
    val value: String
)