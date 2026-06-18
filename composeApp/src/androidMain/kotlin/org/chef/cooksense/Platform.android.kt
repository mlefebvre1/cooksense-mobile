package org.chef.cooksense

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformName(): String {
    return "Android"
}

actual val useEmulator: Boolean = BuildConfig.USE_EMULATOR