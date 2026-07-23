package com.baszczyk.intentio.presentation.screen.activation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baszczyk.intentioapp.presentation.components.BasicOutlinedButton

@Composable
fun NavigationButtons(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        BasicOutlinedButton(
            modifier = Modifier.weight(1f),
            label = "WRÓĆ",
            onClick = onPreviousClick
        )
        BasicOutlinedButton(
            modifier = Modifier.weight(1f),
            label = "DALEJ",
            onClick = onNextClick
        )
    }
}