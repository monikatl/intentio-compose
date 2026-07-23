package com.baszczyk.intentio.domain.repository

import com.baszczyk.intentio.domain.model.Author
import com.google.firebase.firestore.FirebaseFirestore

class AuthorRepository(
    private val db: FirebaseFirestore
) {
    fun getAllAuthors(onResult: (List<Author>) -> Unit) {
        db.collection("authors")
            .get()
            .addOnSuccessListener { result ->
                val authors = result.documents.mapNotNull { it.toObject(Author::class.java) }
                onResult(authors)
            }
            .addOnFailureListener { e ->
                println("Error fetching authors: $e")
                onResult(emptyList())
            }
    }

    fun getAuthorById(authorId: String, onResult: (Author?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("authors")
            .document(authorId)
            .get()
            .addOnSuccessListener { snapshot ->
                val author = snapshot.toObject(Author::class.java)
                onResult(author)
            }
            .addOnFailureListener { e ->
                println("Error fetching: $e")
                onResult(null)
            }
    }

    fun getRandomAuthor(onResult: (Author?) -> Unit) {
        val randomValue = Math.random()

        db.collection("authors")
            .whereGreaterThanOrEqualTo("randomKey", randomValue)
            .limit(1)
            .get()
            .addOnSuccessListener { result ->
                val author = result.documents.firstOrNull()?.toObject(Author::class.java)
                println(author?.name)
                if (author != null) {
                    onResult(author)
                } else {
                    db.collection("authors")
                        .whereLessThan("randomKey", randomValue)
                        .limit(1)
                        .get()
                        .addOnSuccessListener { fallback ->
                            onResult(fallback.documents.firstOrNull()?.toObject(Author::class.java))
                        }
                }
            }
            .addOnFailureListener { e ->
                println("Error fetching random author: $e")
                onResult(null)
            }
    }

}