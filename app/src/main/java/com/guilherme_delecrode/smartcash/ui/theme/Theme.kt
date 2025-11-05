package com.guilherme_delecrode.smartcash.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = TextPrimary,
    secondary = TextSecondary,
    background = backgroundLigh,
    surface = backgroundLigh,
    error = Negative,
    onPrimary = backgroundLigh,
    onSecondary = backgroundLigh,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = TextPrimaryDark,
    secondary = TextSecondaryDark,
    background = backgroundDark,
    surface = backgroundDark,
    error = Negative,
    onPrimary = backgroundDark,
    onSecondary = backgroundDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    onError = Color.Black
)

@Composable
fun SmartCashTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}