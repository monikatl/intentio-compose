package com.baszczyk.intentio.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LiturgicalDay(
    val date: String,
    val season: String? = null,
    val celebration: String? = null
)