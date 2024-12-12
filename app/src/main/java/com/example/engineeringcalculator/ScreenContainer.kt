package com.example.engineeringcalculator

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.engineeringcalculator.screens.BasicCalculator
import com.example.engineeringcalculator.screens.Graph

import com.example.engineeringcalculator.screens.MatrixAndComplexNumber
import com.example.engineeringcalculator.screens.OrientationScreen
import com.example.engineeringcalculator.screens.ScientificScreen
import com.example.engineeringcalculator.screens.Screens
import com.example.engineeringcalculator.ui.theme.localCustomColorScheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun ScreenContainer() {
    val navController = rememberNavController()
    var currentScreen by rememberSaveable { mutableStateOf(Screens.BasicCalculator) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContents(screens = Screens.entries, currentScreen = currentScreen, onItemClick = { screen ->
                currentScreen = screen
                scope.launch {
                    drawerState.close()
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            })
        },
        scrimColor = Color.Transparent
    ) {
        NavHost(navController = navController, startDestination = Screens.BasicCalculator.route) {
            composable(route = Screens.BasicCalculator.route) {
                OrientationScreen(isLandscape = false) {
                    BasicCalculator(onNavigationIconClick = {scope.launch { drawerState.open()}})
                }
            }
            composable(route = Screens.ScientificCalculator.route) {
                OrientationScreen(isLandscape = true) {
                    ScientificScreen(onNavigationIconClick = {scope.launch { drawerState.open()}})
                }
            }
            composable(route = Screens.Graph.route) {
                Graph()
            }
            composable(route = Screens.MatrixAndComplexNumber.route) {
                MatrixAndComplexNumber()
            }
            composable(route = Screens.MathSolver.route) {

            }
        }
    }
}

@Composable
fun DrawerContents(
    screens: List<Screens>,
    currentScreen: Screens,
    onItemClick: (Screens) -> Unit
) {
    val currentConfiguration = LocalConfiguration.current.orientation
    val colors = localCustomColorScheme.current
    val shadowColor = colors.basicUpperPanelShadow // Semi-transparent black
    val shadowOffset = Offset(4f, 0f) // Shadow direction (left and up)
    val shadowRadius = 40f// Shadow blur radius
    Card(modifier = Modifier
        .drawBehind {
            val paint = Paint().apply {
                color = shadowColor
                asFrameworkPaint().setShadowLayer(
                    shadowRadius,
                    shadowOffset.x,
                    shadowOffset.y,
                    shadowColor.toArgb()
                )
            }
            drawIntoCanvas { canvas ->
                canvas.drawRoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    radiusX = 37.38.dp.toPx(),
                    radiusY = 37.38.dp.toPx(),
                    paint = paint
                )
            }
        },
        shape = RoundedCornerShape(bottomEnd = 37.38.dp, topEnd = 37.38.dp),
        colors = CardDefaults.cardColors(containerColor = colors.basicUpperPanelBackground),
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth( if (currentConfiguration == Configuration.ORIENTATION_LANDSCAPE) 0.37f
                else 0.75f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Spacer(modifier = Modifier.height(50.dp))
            for (screen in screens) {
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text(text = screen.route, color = if (currentScreen == screen) Color.Green else colors.basicNumberButtonKey) },
                    selected = false,
                    onClick = { onItemClick(screen) },
                    shape = RoundedCornerShape(16.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                    )
                )
            }
        }
    }
}