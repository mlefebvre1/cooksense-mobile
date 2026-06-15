package org.chef.cooksense.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable

// ── Red palette ──────────────────────────────────────────
val Red900 = Color(0xFF7B1515)
val Red800 = Color(0xFF8F1C1C)
val Red700 = Color(0xFFA32D2D)  // primary
val Red600 = Color(0xFFBF4040)
val Red500 = Color(0xFFD95B5B)
val Red400 = Color(0xFFE24B4A)
val Red200 = Color(0xFFF09595)
val Red100 = Color(0xFFF7C1C1)
val Red50 = Color(0xFFFCEBEB)

// ── Neutral palette ──────────────────────────────────────
val Neutral900 = Color(0xFF1A1A1A)
val Neutral800 = Color(0xFF2C2C2C)
val Neutral700 = Color(0xFF3D3D3D)
val Neutral600 = Color(0xFF5C5C5C)
val Neutral400 = Color(0xFF9E9E9E)
val Neutral200 = Color(0xFFE0E0E0)
val Neutral100 = Color(0xFFF5F5F5)
val Neutral50 = Color(0xFFFAFAFA)

private val LightColorScheme = lightColorScheme(
    primary = Red700,
    onPrimary = Color.White,
    primaryContainer = Red50,
    onPrimaryContainer = Red900,

    secondary = Red500,
    onSecondary = Color.White,
    secondaryContainer = Red100,
    onSecondaryContainer = Red900,

    tertiary = Red400,
    onTertiary = Color.White,
    tertiaryContainer = Red50,
    onTertiaryContainer = Red800,

    error = Color(0xFFB3261E),
    onError = Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    background = Color.White,
    onBackground = Neutral900,

    surface = Neutral50,
    onSurface = Neutral900,
    surfaceVariant = Neutral100,
    onSurfaceVariant = Neutral600,

    outline = Neutral400,
    outlineVariant = Neutral200,

    inverseSurface = Neutral900,
    inverseOnSurface = Neutral50,
    inversePrimary = Red200,

    scrim = Color(0xFF000000),
)

private val DarkColorScheme = darkColorScheme(
    primary = Red200,
    onPrimary = Red900,
    primaryContainer = Red800,
    onPrimaryContainer = Red50,

    secondary = Red200,
    onSecondary = Red800,
    secondaryContainer = Red700,
    onSecondaryContainer = Red50,

    tertiary = Red100,
    onTertiary = Red800,
    tertiaryContainer = Red900,
    onTertiaryContainer = Red100,

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF9DEDC),

    background = Neutral900,
    onBackground = Neutral100,

    surface = Neutral800,
    onSurface = Neutral100,
    surfaceVariant = Neutral700,
    onSurfaceVariant = Neutral400,

    outline = Neutral600,
    outlineVariant = Neutral700,

    inverseSurface = Neutral100,
    inverseOnSurface = Neutral900,
    inversePrimary = Red700,

    scrim = Color(0xFF000000),
)

@Composable
fun CooksenseTheme(
    lightTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (lightTheme) LightColorScheme else DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography(),
    )
}
