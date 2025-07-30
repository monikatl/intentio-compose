package com.baszczyk.intentioapp.domain.model

enum class IntentKind(val text: String, val description: String){
    FOR_THE_DEAD("za zmarłych", ""),
    THANKSGIVING("dziękczynna", ""),
    SUPPLICATORY("błagalna", ""),
    OTHER("inna", "")
}