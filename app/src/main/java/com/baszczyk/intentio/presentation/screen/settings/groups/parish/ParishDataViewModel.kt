package com.baszczyk.intentioapp.presentation.screen.settings.groups.parish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentio.domain.repository.ParishRepository
import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentio.domain.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ParishDataViewModel(
    private val repository: ParishRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ParishDataUiState())
    val uiState: StateFlow<ParishDataUiState> = _uiState.asStateFlow()

    init {
        loadParishData()
    }

    fun deleteMassPattern(massPattern: MassPattern) {
        viewModelScope.launch {
            val massesPattern = _uiState.value.parish?.massesPattern?.toMutableList()
            massesPattern?.remove(massPattern)
            _uiState.value = _uiState.value.copy(
                parish = _uiState.value.parish?.copy(
                    massesPattern = massesPattern ?: emptyList()
                )
            )
        }
    }

    fun addMassPattern(massPattern: MassPattern) {
        viewModelScope.launch {
            val massesPattern = _uiState.value.parish?.massesPattern?.toMutableList()
            massesPattern?.add(massPattern)
            _uiState.value = _uiState.value.copy(
                parish = _uiState.value.parish?.copy(
                    massesPattern = massesPattern ?: emptyList()
                )
            )
        }
    }

    fun editParishData(label: String, value: String) {
        when(label) {
            "Nazwa parafii" -> editParishName(value)
            "Adres parafii" -> editParishAddress(value)
            "Proboszcz" -> editParishPriest(Person(value, "", ""))
        }
    }

    private fun editParishName(
        name: String
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                parish = _uiState.value.parish?.copy(
                    name = name
                )
            )
        }
    }

    private fun editParishAddress(
        address: String
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                parish = _uiState.value.parish?.copy(
                    address = address
                )
            )
        }
    }

    private fun editParishPriest(
        parishPriest: Person
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                parish = _uiState.value.parish?.copy(
                    parishPriest = parishPriest
                )
            )
        }
    }

    private fun loadParishData() {
        viewModelScope.launch {
            repository.getParish {
                _uiState.value = _uiState.value.copy(parish = it)
            }
        }
    }
}