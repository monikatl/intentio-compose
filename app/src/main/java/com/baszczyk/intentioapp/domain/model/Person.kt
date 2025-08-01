package com.baszczyk.intentioapp.domain.model

data class Person (
    val name: String,
    val firstName: String,
    val email: String
) {
    fun getFullName() = "$firstName $name"
}