package com.baszczyk.intentioapp.domain.model

import java.time.LocalDate

data class Mass (
    val hour: Hour,
    val date: LocalDate,
    val day: MassDay
)

enum class MassDay {
    ORDINARY_DAY,
    FEAST
}