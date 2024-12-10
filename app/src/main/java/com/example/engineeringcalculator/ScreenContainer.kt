package com.example.engineeringcalculator

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.engineeringcalculator.screens.BasicCalculator
import com.example.engineeringcalculator.screens.DrawerContents
import com.example.engineeringcalculator.screens.Graph
import com.example.engineeringcalculator.screens.MathSolver
import com.example.engineeringcalculator.screens.MatrixAndComplexNumber
import com.example.engineeringcalculator.screens.OrientationScreen
import com.example.engineeringcalculator.screens.ScientificScreen
import com.example.engineeringcalculator.screens.Screens
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
                MathSolver()
            }
        }
    }
}