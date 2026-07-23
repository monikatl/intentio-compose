package com.baszczyk.intentioapp.presentation.screen.home

import com.baszczyk.intentio.domain.model.Intent

data class HomeUiState (
    val intents: List<Intent> = emptyList(),
    val isLoading: Boolean = false
)