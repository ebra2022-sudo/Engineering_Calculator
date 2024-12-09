package com.example.engineeringcalculator

import androidx.compose.foundation.background
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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

import com.example.engineeringcalculator.screens.Screens

@Composable
fun ScreenContainer(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.BasicCalculator.name) {
        composable(route = Screens.BasicCalculator.name) {
            OrientationScreen(false) {
                BasicCalculator(navController = navController)
            }
        }
        composable(route = Screens.ScientificCalculator.name) {
            //Scientific()
        }
        composable(route = Screens.Graph.name) {
            Graph()
        }
        composable(route = Screens.MatrixAndComplexNumber.name) {
            MatrixAndComplexNumber()
        }
        composable(route = Screens.MathSolver.name) {
            MathSolver()
        }

    }

}