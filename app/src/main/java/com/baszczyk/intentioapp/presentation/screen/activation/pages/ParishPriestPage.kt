package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.presentation.screen.activation.ActivationViewModel
import com.baszczyk.intentioapp.presentation.screen.activation.components.ActivationInputWithButton

@Composable
fun ParishPriestPage(
    onSaveButtonClick: () -> Unit
) {
    ActivationInputWithButton (
        header = "Wprodzadź nazwę parafii: ",
        label = "Proboszcz",
        onSaveClick = onSaveButtonClick
    )
}