package com.baszczyk.intentio.presentation.screen.activation.pages

import androidx.compose.runtime.Composable
import com.baszczyk.intentioapp.R
import com.baszczyk.intentio.presentation.screen.activation.components.ActivationInputCard

@Composable
fun ParishAddressPage(
    addressSave: (String) -> Unit
) {
    ActivationInputCard (
        label = "Adres parafii",
        image = R.drawable.location,
        onSave = { addressSave(it) }
    )
}