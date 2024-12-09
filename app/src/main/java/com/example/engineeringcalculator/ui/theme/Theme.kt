package com.example.engineeringcalculator.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class CustomColorScheme(
    val basicUpperPanelBackground: Color,
    val basicUpperPanelShadow: Color,
    val basicScreenBackground: Color,
    val basicNumberButtonBackground: Color,
    val basicNumberButtonKey: Color,
    val basicOperatorButtonKey: Color,
    val basicOperatorButtonBackground: Color,
    val scientificBackground: Color,
    val iconButton: Color,
    val operator:Color,
    val cancel:Color,
    val equalKey:Color
)
val localLightColorScheme = compositionLocalOf {
    CustomColorScheme(
        basicUpperPanelBackground = basicUpperPanelBackgroundLight,
        basicUpperPanelShadow = basicUpperPanelShadowLight,
        basicScreenBackground = basicScreenBackgroundLight,
        basicNumberButtonBackground = basicNumberButtonBackgroundLight,
        basicNumberButtonKey = basicNumberButtonKeyLight,
        basicOperatorButtonKey = basicOperatorButtonKeyLight,
        basicOperatorButtonBackground = basicOperatorButtonBackgroundLight,
        scientificBackground = scientificBackgroundLight,
        iconButton = iconButtonLight,
        operator = operatorLight,
        cancel = cancelLight,
        equalKey = equalKeyLight
    )
}

val localDarkColorScheme = compositionLocalOf {
    CustomColorScheme(
        basicUpperPanelBackground = basicUpperPanelBackgroundDark,
        basicUpperPanelShadow = basicUpperPanelShadowDark,
        basicScreenBackground = basicScreenBackgroundDark,
        basicNumberButtonBackground = basicNumberButtonBackgroundDark,
        basicNumberButtonKey = basicNumberButtonKeyDark,
        basicOperatorButtonKey = basicOperatorButtonKeyDark,
        basicOperatorButtonBackground = basicOperatorButtonBackgroundDark,
        scientificBackground = scientificBackgroundDark,
        iconButton = iconButtonDark,
        operator = operatorDark,
        cancel = cancelDark,
        equalKey = equalKeyDark
    )
}

val localCustomColorScheme = compositionLocalOf<CustomColorScheme> {
    error("No CustomColorScheme provided")
}




@Composable
fun EngineeringCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Choose the appropriate color scheme based on the theme
    val customColorScheme = if (darkTheme) {
        localDarkColorScheme.current
    } else {
        localLightColorScheme.current
    }

    CompositionLocalProvider(
        localCustomColorScheme provides customColorScheme
    ) {
        MaterialTheme(
            typography = Typography,
            // Optionally use Material's colorScheme for Material components
            colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
            content = content
        )
    }
}