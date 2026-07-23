package com.baszczyk.intentio.domain.model

data class Parish (
    val name: String = "",
    val address: String = "",
    val parishPriest: Person = Person(),
    val massesPattern: List<MassPattern> = emptyList()
)