package com.baszczyk.intentioapp.presentation.screen.settings.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsGroupItem (
    val title: String,
    val description: String,
    val group: SettingsGroup
) : Parcelable

val settings = listOf(
    SettingsGroupItem("Edytuj dane parafii", "Zmień rozkład Mszy Świętych oraz inne informacje dotyczące parafii.", SettingsGroup.PARISH_DATA),
    SettingsGroupItem("Edytuj ustawienia konfiguratora", "Dostosuj wygląd i zachowanie konfiguratora intencji.", SettingsGroup.CONFIGURATOR),
    SettingsGroupItem("Edytuj ustawienia aplikacji", "Edytuj ustawienia aplikacji", SettingsGroup.APPLICATION)
)

@Parcelize
enum class SettingsGroup : Parcelable {
    PARISH_DATA,
    CONFIGURATOR,
    APPLICATION
}

