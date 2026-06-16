package org.chef.cooksense.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.chef.cooksense.repository.Repository
import org.chef.cooksense.repository.firestore.FirestoreRecipeRepository
import org.chef.cooksense.repository.firestore.FirestoreUserRepository

fun createFirestore(useEmulator: Boolean = false): FirebaseFirestore {
    val firestore = Firebase.firestore
    if (useEmulator) {
        println("Using firestore emulator.")
        firestore.useEmulator(emulatorHost, port = 8080)
    }
    return firestore
}

fun firestoreRepository(firestore: FirebaseFirestore): Repository {
    return Repository(
        recipe = FirestoreRecipeRepository(firestore),
        user = FirestoreUserRepository(firestore)
    )
}