package com.baszczyk.intentio.presentation.screen.activation

import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentio.domain.model.Parish
import com.baszczyk.intentio.domain.model.Person

data class ActivationUiState (
    val name: String? = null,
    val address: String? = null,
    val priest: Person? = null,
    val massPatterns: List<MassPattern> = emptyList(),
    val parish: Parish? = null,
    val priests: List<Person> = emptyList()
)