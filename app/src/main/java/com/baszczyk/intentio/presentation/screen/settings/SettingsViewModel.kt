package com.baszczyk.intentioapp.presentation.screen.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baszczyk.intentioapp.datastore.DataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    val showDescriptions: StateFlow<Boolean> = dataStoreManager.settingsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun setShowDescriptions(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setShowDescriptions(enabled)
        }
    }
}