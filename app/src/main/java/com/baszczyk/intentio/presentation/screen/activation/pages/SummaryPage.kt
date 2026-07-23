package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.baszczyk.intentio.presentation.screen.activation.ActivationUiState

@Composable
fun SummaryPage(
    state: ActivationUiState
) {
    Column {
        Text(text = state.name ?: "")
        Text(text = state.address ?: "")
        Text(text = state.priest?.firstName ?: "")
    }
}