package org.chef.cooksense

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformName(): String {
    return "iOS"
}

actual val useEmulator: Boolean = false // change manually when needed