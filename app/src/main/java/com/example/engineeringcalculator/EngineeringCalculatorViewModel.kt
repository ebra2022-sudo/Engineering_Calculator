package com.example.engineeringcalculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EngineeringCalculatorViewModel: ViewModel() {
    val expression = mutableStateOf("")
    val onButtonClick: (String) -> Unit = {expression.value = expression.value + it}
    val onClearClick: () -> Unit = {expression.value = expression.value.dropLast(1) }
    val onHistoryClick: () -> Unit = {}
    val onCancelClick: () -> Unit = { expression.value = ""}

    fun formatExpression(input: String): String {
        val operators = setOf('+', '-', '×', '÷', '%')
        val formatted = StringBuilder()
        var openParenthesesCount = 0

        for ((index, char) in input.withIndex()) {
            when {
                char == '(' -> {
                    formatted.append(if (index > 0 && input[index - 1].isDigit()) " × (" else "(")
                    openParenthesesCount++
                }
                char == ')' -> {
                    if (openParenthesesCount > 0) {
                        formatted.append(")")
                        openParenthesesCount--
                    }
                }
                char in operators -> {
                    // Avoid duplicate operators
                    if (formatted.isNotEmpty() && formatted.last() !in operators) {
                        formatted.append(" $char ")
                    }
                }
                char.isDigit() || char == '.' -> {
                    // Prevent invalid '.' placement (like multiple decimals in a number)
                    if (char == '.' && formatted.isNotEmpty() && formatted.last() == '.') {
                        continue
                    }
                    formatted.append(char)
                }
                char == '+' && index > 0 && input[index - 1] == '(' -> {
                    // Handle optional + after opening parenthesis
                    continue
                }
                else -> continue // Ignore invalid characters
            }
        }

        // Add closing parentheses if unbalanced
        repeat(openParenthesesCount) {
            formatted.append(")")
        }

        return formatted.toString().trim()
    }

}