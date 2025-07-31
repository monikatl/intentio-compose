package com.baszczyk.intentioapp.presentation.screen.settings.model

data class SettingsGroupItem (
    val title: String,
    val description: String,
    val group: SettingsGroup
)

val settings = listOf(
    SettingsGroupItem("Edytuj dane parafii", "Zmień rozkład Mszy Świętych oraz inne informacje dotyczące parafii.", SettingsGroup.PARISH_DATA),
    SettingsGroupItem("Edytuj ustawienia konfiguratora", "Dostosuj wygląd i zachowanie konfiguratora intencji.", SettingsGroup.CONFIGURATOR),
    SettingsGroupItem("Edytuj ustawienia aplikacji", "Edytuj ustawienia aplikacji", SettingsGroup.APPLICATION)
)

enum class SettingsGroup {
    PARISH_DATA,
    CONFIGURATOR,
    APPLICATION
}

