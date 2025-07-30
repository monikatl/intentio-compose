package com.baszczyk.intentioapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    CONFIGURATOR("configurator", "Konfigurator", Icons.Default.Build, "Konfigurator"),
    HOME("home", "Intencje", Icons.Default.Home, "Intencje"),
    SETTINGS("settings", "Ustawienia", Icons.Default.Settings, "Ustawienia")
}