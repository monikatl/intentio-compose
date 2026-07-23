package com.baszczyk.intentio.presentation.screen.activation

import com.baszczyk.intentioapp.R

data class SuccessUiState(
    val image: Int = R.drawable.done,
    val message: String = "",
    val buttonLabel: String = "",
    val contactButton: String? = null
)