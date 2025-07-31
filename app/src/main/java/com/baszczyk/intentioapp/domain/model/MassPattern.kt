package com.baszczyk.intentioapp.domain.model

data class MassPattern (
    val hour: String,
    val day: MassDay
)

enum class MassDay {
    ORDINARY_DAY,
    FEAST
}