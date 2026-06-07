package org.chef.cooksense.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun CooksenseTheme(
    lightTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (lightTheme) {
        lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFFA32D2D),
            secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6),
            background = androidx.compose.ui.graphics.Color(0xFFFFFBFE),
            surface = androidx.compose.ui.graphics.Color(0xFFFFFBFE),
            onPrimary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
            onSecondary = androidx.compose.ui.graphics.Color(0xFF000000),
            onBackground = androidx.compose.ui.graphics.Color(0xFF000000),
            onSurface = androidx.compose.ui.graphics.Color(0xFF000000),
        )
    } else {
        darkColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
            secondary = androidx.compose.ui.graphics.Color(0xFF03DAC6),
            background = androidx.compose.ui.graphics.Color(0xFF000000),
            surface = androidx.compose.ui.graphics.Color(0xFF000000),
            onPrimary = androidx.compose.ui.graphics.Color(0xFF000000),
            onSecondary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
            onBackground = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
            onSurface = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
        )
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
