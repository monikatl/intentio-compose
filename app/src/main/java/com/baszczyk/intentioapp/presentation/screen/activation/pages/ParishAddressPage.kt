package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.presentation.screen.activation.ActivationViewModel
import com.baszczyk.intentioapp.presentation.screen.activation.components.ActivationInputWithButton

@Composable
fun ParishAddressPage(
    onSaveButtonClick: () -> Unit
) {
    ActivationInputWithButton (
        header = "Wprodzadź adres parafii: ",
        label = "Adres parafii",
        onSaveClick = onSaveButtonClick
    )
}