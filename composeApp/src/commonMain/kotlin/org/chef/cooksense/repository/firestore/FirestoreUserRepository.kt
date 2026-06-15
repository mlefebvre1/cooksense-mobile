package org.chef.cooksense.repository.firestore

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.repository.UserRepository

class FirestoreUserRepository(private val repository: FirebaseFirestore) : UserRepository {
    private val auth = Firebase.auth

    private fun userDoc() = repository
        .collection("users")
        .document(auth.currentUser?.uid ?: error("User not logged in"))

    override suspend fun fetchFavorites(): List<Recipe> {
        return userDoc()
            .collection("favorites")
            .get()
            .documents
            .map { it.data<Recipe>() }
    }

    override suspend fun addToFavorites(recipe: Recipe) {
        userDoc().collection("favorites").document(recipe.id).set(recipe)
    }

    override suspend fun removeFromFavorites(recipe: Recipe) {
        userDoc().collection("favorites").document(recipe.id).delete()
    }

    override suspend fun fetchBlacklist(): List<Recipe> {
        return userDoc()
            .collection("blacklist")
            .get()
            .documents
            .map { it.data<Recipe>() }
    }

    override suspend fun addToBlacklist(recipe: Recipe) {
        userDoc().collection("blacklist").document(recipe.id).set(recipe)
    }

    override suspend fun removeFromBlacklist(recipe: Recipe) {
        userDoc().collection("blacklist").document(recipe.id).delete()
    }
}