package org.chef.cooksense.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class AuthRepository {
    private val auth = Firebase.auth

    suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    suspend fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun signOut() = auth.signOut()

    val currentUser get() = auth.currentUser
}