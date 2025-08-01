package com.baszczyk.intentioapp.domain.model

data class Parish (
    val name: String,
    val address: String,
    val parishPriest: Person,
    val massesPattern: List<MassPattern>
)