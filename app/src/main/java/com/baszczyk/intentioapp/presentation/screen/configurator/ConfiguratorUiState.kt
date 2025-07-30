package com.baszczyk.intentioapp.presentation.screen.configurator

import com.baszczyk.intentioapp.domain.model.Hour
import com.baszczyk.intentioapp.domain.model.IntentKind
import com.baszczyk.intentioapp.domain.model.IntentType
import com.baszczyk.intentioapp.domain.model.Mass
import com.baszczyk.intentioapp.domain.model.Person
import java.time.LocalDate

data class ConfiguratorUiState (
    val type: IntentType? = IntentType.SINGLE,
    val kind: IntentKind = IntentKind.SUPPLICATORY,
    val date: LocalDate? = null,
    val hour: Hour? = null,
    val mass: Mass? = null,
    val header: String? = null,
    val fromWhom: String? = null,
    val orderer: Person? = null,
    val showIntentBottomSheet: Boolean = false,
    val masses: List<Mass> = emptyList(),
    val headers: List<String> = emptyList(),
    val fromWhomMap: Map<String, List<String>> = emptyMap(),
    val regexList: List<String> = emptyList(),
    val currentRegex: List<String> = emptyList()
)