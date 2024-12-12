package com.example.engineeringcalculator.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InteractiveAxes() {
    val density = LocalDensity.current
    var rotation by remember { mutableStateOf(0f) }
    var translation by remember { mutableStateOf(Offset.Zero) }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSize = size
        val halfWidth = canvasSize.width / 2f
        val halfHeight = canvasSize.height / 2f

        // Draw x-axis
        drawLine(
            color = Color.Blue,
            start = Offset(halfWidth, halfHeight),
            end = Offset(halfWidth, canvasSize.height - halfHeight)
        )

        // Draw y-axis
        drawLine(
            color = Color.Red,
            start = Offset(halfWidth, halfHeight),
            end = Offset(canvasSize.width - halfWidth, halfHeight)
        )

        // Draw z-axis (optional, can be represented as a 3D cube or other shape)
        drawLine(
            color = Color.Green,
            start = Offset(halfWidth, halfHeight),
            end = Offset(halfWidth, halfHeight + 100f * density.density)
        )

        // Rotate the axes
        rotate(rotation) {
            // Draw rotated axes
        }

        // Translate the axes
        translate(left = translation.x, top = translation.y) {
            // Draw translated axes
        }
    }

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Rotate: $rotation")
        Text(text = "Translate: $translation")
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    InteractiveAxes()
    
}
