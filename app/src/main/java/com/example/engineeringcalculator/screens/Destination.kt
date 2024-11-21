package com.example.engineeringcalculator.screens

sealed class Screens(name: String) {
    data object BasicCalculator: Screens(name = "Basic Calculator")
    data object ScientificCalculator: Screens(name = "Scientific Calculator")
    data object Graph: Screens(name = "Graph")
    data object MatrixAndComplexNumber: Screens(name = "Matrix and Complex Number")
    data object MathSolver: Screens(name = "Math Solver")
}