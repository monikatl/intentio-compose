package com.baszczyk.intentioapp.domain.model

data class Intent (
    val type: IntentType?,
    val mass: Mass?,
    val content: Content?,
    val orderer: Person?
)