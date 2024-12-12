package com.example.engineeringcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.example.engineeringcalculator.ui.theme.EngineeringCalculatorTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            val insetsController = WindowCompat.getInsetsController(window, view)
            // Check if the system is in dark mode
            val isSystemInDarkMode = (resources.configuration.uiMode and
                    android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
                    android.content.res.Configuration.UI_MODE_NIGHT_YES

            // Set the status bar icons based on the mode
            insetsController.isAppearanceLightStatusBars = !isSystemInDarkMode
            insetsController.isAppearanceLightNavigationBars = !isSystemInDarkMode

            windowInsets
        }
        setContent {
            EngineeringCalculatorTheme {
                ScreenContainer()
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EngineeringCalculatorTheme {
        Greeting("Android")
    }
}