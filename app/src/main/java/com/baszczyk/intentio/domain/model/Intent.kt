package com.baszczyk.intentio.domain.model

import com.baszczyk.intentioapp.domain.model.Mass

data class Intent (
    val type: IntentType?,
    val mass: Mass?,
    val content: Content?,
    val orderer: Person?
)