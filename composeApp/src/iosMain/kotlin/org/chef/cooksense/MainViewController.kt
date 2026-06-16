package org.chef.cooksense

import androidx.compose.ui.window.ComposeUIViewController
import org.chef.cooksense.firebase.createFirestore
import org.chef.cooksense.firebase.firestoreRepository

fun MainViewController() = ComposeUIViewController {
    App(
        repository = firestoreRepository(
            firestore = createFirestore(useEmulator = false)
        )
    )
}