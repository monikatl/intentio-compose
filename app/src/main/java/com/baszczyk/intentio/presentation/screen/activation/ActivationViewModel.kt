package com.baszczyk.intentio.presentation.screen.activation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentio.domain.model.Parish
import com.baszczyk.intentio.domain.repository.ParishRepository
import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentio.domain.model.Person
import com.baszczyk.intentio.presentation.screen.activation.ActivationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActivationViewModel(
    private val repository: ParishRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ActivationUiState())
    val uiState: StateFlow<ActivationUiState> = _uiState.asStateFlow()

    fun saveParishName(name: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(name = name)
        }
    }

    fun saveParishAddress(address: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(address = address)
        }
    }

    fun saveParishPriest(priest: Person) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(priest = priest)
        }
    }

    fun savePriests(priests: List<Person>) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(priests = priests)
        }
    }

    fun saveParishMassPatterns(patterns: List<MassPattern>) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(massPatterns = patterns)
        }
    }

    fun collectParishDataAndSend() {
        viewModelScope.launch {
            val parishData = _uiState.value
            val parish = Parish(
                name = parishData.name ?: "",
                address = parishData.address ?: "",
                parishPriest = parishData.priest ?: Person("", "", ""),
                massesPattern = parishData.massPatterns
            )
//            repository.saveParish(parish) {
//                println("SUKCESIK")
//            }
        }
    }
}