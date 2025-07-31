package com.baszczyk.intentioapp.presentation.screen.settings.groups

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsToggleItem
import com.baszczyk.intentioapp.presentation.screen.settings.SettingsViewModel

@Composable
fun ConfiguratorSettings(
    viewModel: SettingsViewModel
) {
    val showDescriptions by viewModel.showDescriptions.collectAsState()
    SettingsToggleItem(
        checked = showDescriptions,
        label = "Rozwijaj opisy",
        description = "Ustaw domyślne wyświetlanie opisów typów i rodzajów intencji",
        toggle = { viewModel.setShowDescriptions(!showDescriptions) }
    )
}
