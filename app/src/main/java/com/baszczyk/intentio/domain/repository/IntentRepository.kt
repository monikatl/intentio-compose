package com.baszczyk.intentio.domain.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.domain.utils.toMap
import com.baszczyk.intentio.domain.model.Intent
import com.baszczyk.intentio.domain.utils.toIntent
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class IntentRepository(
    private val db: FirebaseFirestore
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getIntents(): List<Intent> = suspendCoroutine { cont ->
        db.collection("intents")
            .get()
            .addOnSuccessListener { result ->
                val intents = result.documents.mapNotNull { doc -> doc.data?.toIntent() }
                cont.resume(intents)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Błąd pobierania intentów", e)
                cont.resume(emptyList())
            }
    }

    fun saveIntent(intent: Intent, onSuccess: () -> Unit, onError: () -> Unit) {
        db.collection("intents")
            .add(intent.toMap())
            .addOnSuccessListener { _ ->
                onSuccess()
            }
            .addOnFailureListener { _ ->
                onError()
            }
    }
}