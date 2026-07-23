package com.baszczyk.intentio.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.domain.model.Content
import com.baszczyk.intentio.domain.model.Intent
import com.baszczyk.intentio.domain.model.IntentKind
import com.baszczyk.intentio.domain.model.IntentType
import com.baszczyk.intentio.domain.model.MassPattern
import com.baszczyk.intentioapp.domain.model.Mass
import com.baszczyk.intentio.domain.model.Person
import java.time.LocalDate

fun Intent.toMap(): Map<String, Any?> {
    return mapOf(
        "type" to type?.name, // enum as string
        "mass" to mass?.toMap(),
        "content" to content?.toMap(),
        "orderer" to orderer?.toMap()
    )
}

fun Mass.toMap(): Map<String, Any?> {
    return mapOf(
        "date" to date.toString(), // ISO string
        "massPattern" to massPattern.hour
    )
}

fun Content.toMap(): Map<String, Any?> {
    return mapOf(
        "kind" to kind.name,
        "header" to header,
        "persons" to persons.map { it.toMap() },
        "text" to text
    )
}

fun Person.toMap(): Map<String, Any?> {
    return mapOf(
        "name" to name,
        "firstName" to firstName,
        "email" to email
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Map<String, Any?>.toIntent(): Intent {
    val type = (this["type"] as? String)?.let { IntentType.valueOf(it) }
    val massMap = this["mass"] as? Map<String, Any?>
    val mass = massMap?.let {
        val dateStr = it["date"] as String
        val massPattern = MassPattern()
        Mass(LocalDate.parse(dateStr), massPattern)
    }
    val contentMap = this["content"] as? Map<String, Any?>
    val content = contentMap?.let {
        val kind = IntentKind.valueOf(it["kind"] as String)
        val header = it["header"] as String
        val text = it["text"] as String
        val personsList = (it["persons"] as? List<Map<String, Any?>>)?.map { pMap ->
            Person(
                name = pMap["name"] as String,
                firstName = pMap["firstName"] as String,
                email = pMap["email"] as String
            )
        } ?: emptyList()
        Content(kind, header, personsList, text)
    }
    val ordererMap = this["orderer"] as? Map<String, Any?>
    val orderer = ordererMap?.let {
        Person(
            name = it["name"] as String,
            firstName = it["firstName"] as String,
            email = it["email"] as String
        )
    }
    return Intent(type, mass, content, orderer)
}
