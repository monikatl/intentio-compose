package com.baszczyk.intentioapp.presentation.screen.configurator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentioapp.data.for_dead_headers
import com.baszczyk.intentioapp.data.fromWhomMap
import com.baszczyk.intentioapp.data.intents
import com.baszczyk.intentioapp.data.masses
import com.baszczyk.intentioapp.data.regex
import com.baszczyk.intentioapp.data.supplicator_headers
import com.baszczyk.intentioapp.data.thanksqiving_headers
import com.baszczyk.intentioapp.domain.model.Content
import com.baszczyk.intentioapp.domain.model.Hour
import com.baszczyk.intentioapp.domain.model.Intent
import com.baszczyk.intentioapp.domain.model.IntentKind
import com.baszczyk.intentioapp.domain.model.IntentType
import com.baszczyk.intentioapp.domain.model.Mass
import com.baszczyk.intentioapp.domain.model.Person
import com.baszczyk.intentioapp.presentation.screen.configurator.pages.Pages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ConfiguratorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConfiguratorUiState())
    val uiState: StateFlow<ConfiguratorUiState> = _uiState.asStateFlow()

    fun setIntentType(type: IntentType) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(type = type)
        }
    }

    fun setIntentDateAndHourType(date: LocalDate, hour: Hour, mass: Mass) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(date = date, hour = hour, mass = mass)
        }
    }

    fun setIntentKind(kind: IntentKind) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(kind = kind)
        }
    }

    fun setIntentHeader(header: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(header = header)
        }
    }

    fun setCurrentRegex() {
        val header = _uiState.value.header ?: ""
        val currentRegex = "\\[(.*?)]".toRegex().findAll(header).map { it.groupValues[1] }.toList()
        _uiState.value = _uiState.value.copy(currentRegex = currentRegex)
    }

    fun checkIfHeaderHasRegex(): Boolean {
        setCurrentRegex()
        return _uiState.value.regexList.any { regex -> _uiState.value.header?.contains(regex) == true }
    }

    fun setIntentFromWhom(fromWhom: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(fromWhom =  fromWhom)
        }
    }

    fun toggleIntentBottomSheet() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(showIntentBottomSheet = !_uiState.value.showIntentBottomSheet)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadMasses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(masses = masses)
        }
    }

    fun loadIntentHeaders() {
        viewModelScope.launch {
            val headers = resolveIntentHeaders()
            _uiState.value = _uiState.value.copy(headers = headers)
        }
    }

    fun loadIntentFromWhom() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(fromWhomMap = fromWhomMap)
        }
    }

    fun loadIntentRegex() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(regexList = regex)
        }
    }

    private fun resolveIntentHeaders() =
        when(_uiState.value.kind) {
            IntentKind.FOR_THE_DEAD -> for_dead_headers
            IntentKind.SUPPLICATORY -> supplicator_headers
            IntentKind.THANKSGIVING -> thanksqiving_headers
            IntentKind.OTHER -> emptyList()
        }

    fun resolveNextPageAfterDate() =
        if(_uiState.value.type == IntentType.GREGORIAN) Pages.CONTENT else Pages.KIND

    fun setIntentOrderer(firstName: String, name: String, email: String) {
        viewModelScope.launch {
            val orderer = Person(firstName, name, email)
            _uiState.value = _uiState.value.copy(orderer = orderer)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAndSendIntent() {
        with(_uiState.value) {
            val intent = Intent(
                type = type,
                mass = mass,
                content = header?.let { Content(kind, it, emptyList(), "") },
                orderer = orderer
            )
            intents.add(intent)
        }
    }


}