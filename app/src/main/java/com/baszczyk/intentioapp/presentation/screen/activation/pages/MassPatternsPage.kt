package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.presentation.screen.activation.components.ActivationInputWithButton

@Composable
fun MassPatternsPage (
    onSaveButtonClick: () -> Unit
) {
    ActivationInputWithButton (
        header = "Wprodzadź nazwę parafii: ",
        label = "Nazwa parafii",
        onSaveClick = onSaveButtonClick
    )
}