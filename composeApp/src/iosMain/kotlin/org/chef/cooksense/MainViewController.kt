package org.chef.cooksense

import androidx.compose.ui.window.ComposeUIViewController
import org.chef.cooksense.firebase.createFirestore
import org.chef.cooksense.firebase.firestoreRepository

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
val isDebug: Boolean = Platform.isDebugBinary

fun MainViewController() = ComposeUIViewController {
    App(
        repository = firestoreRepository(
            firestore = createFirestore(useEmulator = isDebug)
        )
    )
}
