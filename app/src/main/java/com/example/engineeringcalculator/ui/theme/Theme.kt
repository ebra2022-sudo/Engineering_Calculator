package com.example.engineeringcalculator.ui.theme


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

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF121212),
    primary = Color(0xFF222222),
    onPrimary = Color(0xFFFFFFFF),
    scrim = Color(0xFF767474),
    secondary = Color(0xFF3A3A3A),
    onSecondary = Color(0xFF32CD32),
    tertiary = Color.Red,
    onTertiary = Color(0xFF000000)

)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFEEF3EC),
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    scrim = Color(0xFF767474),
    secondary = Color(0xFFDDDDDD),
    onSecondary = Color(0xFF2CBE00),
    tertiary = Color.Red,

)

@Composable
fun EngineeringCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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