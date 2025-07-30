package com.baszczyk.intentioapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BorderCard(
    onCardClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Card (
        modifier = Modifier.clickable {
            onCardClick()
        },
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        ),
        border = BorderStroke(1.dp, color = Color.DarkGray),
        content = content
    )
}