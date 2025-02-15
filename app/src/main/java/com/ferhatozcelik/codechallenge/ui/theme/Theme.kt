package com.ferhatozcelik.codechallenge.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF212121),   // Dark Grey - Accent Color
    secondary = Color(0xFF424242), // Slightly Lighter Grey
    tertiary = Color(0xFF616161),  // Even Lighter Grey
    background = Color(0xFF000000), // Pure Black Background
    surface = Color(0xFF121212),   // Darker Black for Surfaces
    onPrimary = Color.White,       // White text on Primary (Dark Grey)
    onSecondary = Color.White,     // White text on Secondary
    onTertiary = Color.White,      // White text on Tertiary
    onBackground = Color.White,    // White text on Black Background
    onSurface = Color.White       // White text on Surface
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF212121),   // Dark Grey - Accent Color
    secondary = Color(0xFF424242), // Slightly Lighter Grey
    tertiary = Color(0xFF616161),  // Even Lighter Grey
    background = Color(0xFFFFFFFF), // White Background
    surface = Color(0xFFEEEEEE),   // Light Grey for Surfaces
    onPrimary = Color.White,       // White text on Primary (Dark Grey)
    onSecondary = Color.White,     // White text on Secondary
    onTertiary = Color.Black,      // Black text on Tertiary
    onBackground = Color.Black,    // Black text on White Background
    onSurface = Color.Black       // Black text on Light Grey Surface
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Keep dynamic color option
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Assuming you have a Typography defined
        content = content
    )
}