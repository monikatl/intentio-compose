package com.baszczyk.intentioapp.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.adaptive.ListDetailPaneScaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.calculateListDetailPaneScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baszczyk.intentioapp.presentation.screen.settings.groups.ApplicationSettings
import com.baszczyk.intentioapp.presentation.screen.settings.groups.ConfiguratorSettings
import com.baszczyk.intentioapp.presentation.screen.settings.groups.ParishDataSettings
import com.baszczyk.intentioapp.presentation.screen.settings.model.SettingsGroup
import com.baszczyk.intentioapp.presentation.screen.settings.model.settings
import com.baszczyk.intentioapp.ui.theme.Dimension
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreen() {

    val viewModel = koinInject<SettingsViewModel>()

    var selectedSetting by remember { mutableStateOf<SettingsGroup?>(null) }
    val scaffoldState =  calculateListDetailPaneScaffoldState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimension.large)
    ) {
        ListDetailPaneScaffold(
            scaffoldState = scaffoldState,
            listPane = {
                LazyColumn {
                    items(settings) { item ->
                        SettingsGroupItem(
                            label = item.title,
                            description = item.description
                        ) { selectedSetting = item.group }
                    }
                }
            },
            detailPane = {
                when (selectedSetting) {
                    SettingsGroup.PARISH_DATA -> ParishDataSettings()
                    SettingsGroup.CONFIGURATOR -> ConfiguratorSettings(viewModel)
                    SettingsGroup.APPLICATION -> ApplicationSettings()
                    else -> Text("Wybierz ustawienie")
                }
            }
        )
    }
}

@Composable
fun SettingsGroupItem(
    label: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = label, fontSize = 20.sp)
            Text(text = description, fontSize = 16.sp, fontWeight = FontWeight.Thin)
        }

        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "go",
                modifier = Modifier.size(100.dp),
                tint = Color.LightGray
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        thickness = 1.dp,
        color = Color.LightGray
    )
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
            }
        )

    }
}