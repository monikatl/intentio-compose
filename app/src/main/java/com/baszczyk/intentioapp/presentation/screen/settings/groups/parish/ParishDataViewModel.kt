package com.baszczyk.intentioapp.presentation.screen.settings.groups.parish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentioapp.data.parish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ParishDataViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ParishDataUiState())
    val uiState: StateFlow<ParishDataUiState> = _uiState.asStateFlow()

    init {
        loadParishData()
    }

    private fun loadParishData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(parish = parish)
        }
    }
}