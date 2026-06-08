package org.chef.cooksense

import androidx.compose.runtime.Composable
import org.chef.cooksense.ui.CooksenseTheme


@Composable
fun App() {
    CooksenseTheme(lightTheme = true) {
        AppNavigation()
    }
}

