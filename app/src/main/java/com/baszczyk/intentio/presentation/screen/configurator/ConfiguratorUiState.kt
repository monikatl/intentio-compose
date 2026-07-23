package com.baszczyk.intentio.presentation.screen.configurator

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.domain.model.IntentKind
import com.baszczyk.intentio.domain.model.IntentType
import com.baszczyk.intentioapp.domain.model.Mass
import com.baszczyk.intentio.domain.model.Person
import java.time.LocalDate

data class ConfiguratorUiState (
    val intentElements: IntentElements = IntentElements(),
    val showIntentBottomSheet: Boolean = false,
    val masses: List<Mass> = emptyList(),
    val headers: List<String> = emptyList(),
    val fromWhomMap: Map<String, List<String>> = emptyMap(),
    val regexList: List<String> = emptyList(),
    val currentRegex: List<String> = emptyList(),
    val parish: ParishUiState = ParishUiState()
) {
    data class IntentElements(
        val type: IntentType? = IntentType.SINGLE,
        val kind: IntentKind = IntentKind.SUPPLICATORY,
        val date: LocalDate? = null,
        val hour: String? = null,
        val mass: Mass? = null,
        val header: String? = null,
        val fromWhom: String? = null,
        val orderer: Person? = null,
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getFormatDate() = date?.dayOfMonth.toString() + " " + date?.month?.name + " " + date?.year
    }

    data class ParishUiState(
        var isLoading: Boolean = false,
        var success: Boolean = false,
        var error: String? = null
    )
}