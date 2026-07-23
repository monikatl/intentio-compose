package com.baszczyk.intentio.domain.model

data class MassPattern (
    val hour: String = "",
    val day: MassDay = MassDay.ORDINARY_DAY
)

enum class MassDay(val text: String) {
    ORDINARY_DAY("dzień powszedni"),
    FEAST("niedziela")
}