package com.baszczyk.intentioapp.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.R
import com.baszczyk.intentio.presentation.screen.activation.components.ActivationInputCard

@Composable
fun ParishNamePage(
    nameSave: (String) -> Unit
) {
    ActivationInputCard (
        label = "Nazwa parafii",
        image = R.drawable.church,
        onSave = { nameSave(it) }
    )
}
