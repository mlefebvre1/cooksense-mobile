package org.chef.cooksense.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

fun createFirestore(useEmulator: Boolean = false): FirebaseFirestore {
    val firestore = Firebase.firestore
    if (useEmulator) {
        println("Using firestore emulator.")
        firestore.useEmulator("10.0.2.2", 8080)
    }
    return firestore
}