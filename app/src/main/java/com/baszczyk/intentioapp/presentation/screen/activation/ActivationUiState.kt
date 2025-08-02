package com.baszczyk.intentioapp.presentation.screen.activation

import com.baszczyk.intentioapp.domain.model.MassPattern
import com.baszczyk.intentioapp.domain.model.Person

data class ActivationUiState (
    val name: String? = null,
    val address: String? = null,
    val priest: Person? = null,
    val massPatterns: List<MassPattern> = emptyList()
)