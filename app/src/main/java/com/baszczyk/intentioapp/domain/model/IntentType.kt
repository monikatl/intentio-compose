package com.baszczyk.intentioapp.domain.model

enum class IntentType(val text: String, val description: String) {
    SINGLE("indywidualna", "Indywidualna intencja mszalna to ofiarowanie jednej Mszy Świętej w konkretnej intencji (np. za zmarłego, w podziękowaniu, w intencji uzdrowienia), zgłoszonej przez osobę prywatną lub rodzinę."),
    COLLECTIVE("zbiorowa", "Indywidualna intencja mszalna to ofiarowanie jednej Mszy Świętej w konkretnej intencji (np. za zmarłego, w podziękowaniu, w intencji uzdrowienia), zgłoszonej przez osobę prywatną lub rodzinę."),
    GREGORIAN("gregoriańska", "Indywidualna intencja mszalna to ofiarowanie jednej Mszy Świętej w konkretnej intencji (np. za zmarłego, w podziękowaniu, w intencji uzdrowienia), zgłoszonej przez osobę prywatną lub rodzinę.")
}