package com.baszczyk.intentio.domain.model

data class Author(
    val name: String = "",
    val quotes: List<String> = emptyList()
)