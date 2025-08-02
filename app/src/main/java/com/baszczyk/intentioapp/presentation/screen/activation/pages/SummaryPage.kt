package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.presentation.screen.activation.ActivationViewModel

@Composable
fun SummaryPage(
    viewModel: ActivationViewModel,
    onSaveButtonClick: () -> Unit
) {
    Button(
        onSaveButtonClick
    ) {
        Text(text = "ZAPISZ")
    }
}