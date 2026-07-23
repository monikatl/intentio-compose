package com.baszczyk.intentio.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomVerticalSpacer(value: Int) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
fun CustomHorizontalSpacer(value: Int) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
fun SmallHorizontalSpacer() {

}

@Composable
fun SmallVerticalSpacer() = CustomVerticalSpacer(8)

@Composable
fun MediumVerticalSpacer() = CustomVerticalSpacer(16)

@Composable
fun BigVerticalSpacer() = CustomVerticalSpacer(32)

@Composable
fun LargeVerticalSpacer() = CustomVerticalSpacer(64)

