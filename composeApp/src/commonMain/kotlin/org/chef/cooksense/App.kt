package org.chef.cooksense

import androidx.compose.runtime.Composable
import dev.gitlive.firebase.firestore.FirebaseFirestore
import org.chef.cooksense.repository.Repository
import org.chef.cooksense.ui.CooksenseTheme


@Composable
fun App(repository: Repository) {
    CooksenseTheme(lightTheme = true) {
        AppNavigation(repository = repository)
    }
}

