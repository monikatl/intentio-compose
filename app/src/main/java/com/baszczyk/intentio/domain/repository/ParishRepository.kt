package com.baszczyk.intentio.domain.repository

import com.baszczyk.intentio.domain.model.Parish
import com.google.firebase.firestore.FirebaseFirestore

class ParishRepository(
    private val db: FirebaseFirestore
) {
    fun saveParish(parish: Parish, onResult: (Boolean) -> Unit) {
        db.collection("parishes")
            .document("mainParish")
            .set(parish)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun getParish(onResult: (Parish?) -> Unit) {
        db.collection("parishes")
            .document("mainParish")
            .get()
            .addOnSuccessListener { doc ->
                onResult(doc.toObject(Parish::class.java))
            }
            .addOnFailureListener { onResult(null) }
    }
}