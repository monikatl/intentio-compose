package com.baszczyk.intentioapp.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.baszczyk.intentioapp.ui.theme.Dimension
import org.koin.compose.koinInject

@Composable
fun SettingsScreen() {

    val viewModel = koinInject<SettingsViewModel>()
    val showDescriptions by viewModel.showDescriptions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimension.big)
    ) {
        SettingsToggleItem(
            checked = showDescriptions,
            label = "Rozwijaj opisy",
            description = "Ustaw domyślne wyświetlanie opisów typów i rodzajów intencji",
            toggle = { viewModel.setShowDescriptions(!showDescriptions) }
        )
    }

}

@Composable
fun SettingsToggleItem(
    checked: Boolean,
    label: String,
    description: String,
    toggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = label, fontSize = 20.sp)
            Text(text = description, fontSize = 16.sp, fontWeight = FontWeight.Thin)
        }

        Switch(
            checked = checked,
            onCheckedChange = {
                toggle()
                println("WYŚWIETLAM - $checked")
            }
        )

    }
}