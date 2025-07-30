package com.baszczyk.intentioapp.domain.model

data class Content (
    val kind: IntentKind,
    val header: String,
    val persons: List<Person>,
    val text: String
)