package com.pacmacmobile.relay.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val PureBlack = Color(0xFF000000)
val CharcoalDark = Color(0xFF121212)
val CharcoalMedium = Color(0xFF1E1E1E)
val CharcoalLight = Color(0xFF2C2C2C)
val TextWhite = Color(0xFFFFFFFF)
val TextMuted = Color(0xFFAAAAAA)
val Accent = Color(0xFF4A90E2) // Restrained blue accent

data class RelayColors(
    val background: Color = PureBlack,
    val surface: Color = CharcoalDark,
    val surfaceHighlight: Color = CharcoalMedium,
    val textPrimary: Color = TextWhite,
    val textSecondary: Color = TextMuted,
    val accent: Color = Accent,
    val border: Color = CharcoalLight
)

val LocalRelayColors = staticCompositionLocalOf { RelayColors() }

object RelayTheme {
    val colors: RelayColors
        @Composable
        get() = LocalRelayColors.current
    
    val typography: RelayTypography
        @Composable
        get() = LocalRelayTypography.current
}

@Composable
fun RelayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Force dark theme for now as requested
    val colors = RelayColors()
    
    CompositionLocalProvider(
        LocalRelayColors provides colors,
        LocalRelayTypography provides defaultRelayTypography
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                background = colors.background,
                surface = colors.surface,
                primary = colors.accent,
                onPrimary = colors.textPrimary,
                onBackground = colors.textPrimary,
                onSurface = colors.textPrimary
            ),
            content = content
        )
    }
}
