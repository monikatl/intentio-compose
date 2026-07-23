package com.baszczyk.intentio.presentation.screen.activation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentioapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SuccessViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SuccessUiState())
    val uiState: StateFlow<SuccessUiState> = _uiState.asStateFlow()

    fun setSuccess() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                image = R.drawable.done,
                message = "Parafia została zarejestrowana!",
                buttonLabel = "Przejdź do aplikacji"
            )
        }
    }

    fun setFailure() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                image = R.drawable.character,
                message = "Coś poszło nie tak!",
                buttonLabel = "Ponów próbę",
                contactButton = "Kontakt"
            )
        }
    }
}