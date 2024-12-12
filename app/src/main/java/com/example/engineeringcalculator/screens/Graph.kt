package com.example.engineeringcalculator.screens


import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Graph() {
    Scaffold {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            Polygon(
                brush = Brush.linearGradient(colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta)),
                sideLength = 350.dp,
                sides = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                style = Fill,
            )
        }
    }
}











/**
 * This is Composable to Draw Polygon with side length and number of sides >= 3.
 * @sample {
 *  Polygon(sideLength = 50.dp, sides = 5)
 * }
 * @see
 *
 */
@Composable
fun Polygon(modifier: Modifier = Modifier, sideLength: Dp, sides: Int = 3,
            rotation: Float = 0f,
            scale: Float = 1f,
            center: Offset = Offset.Unspecified,
            color: Color = Color.Black,
            scaleX: Float = 1f,
            scaleY: Float = 1f,
            @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f,
            style: DrawStyle = Fill,
            colorFilter: ColorFilter? = null,
            blendMode: BlendMode = DefaultBlendMode, fill: @Composable () -> Unit = {}) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier) {
            var computerCenter = if (center == Offset.Unspecified) Offset(size.width / 2, size.height / 2) else center
            val x_c = computerCenter.x
            val y_c = computerCenter.y
            val path = Path()
            // design the  state 
            path.moveTo(x_c, y_c)
            val r = sideLength.toPx() / (2* sin(PI / sides))
            val firstAngle = (sides - 2) * (PI / (2 * sides))
            path.moveTo((x_c + r * cos(firstAngle)).toFloat(), (y_c + r * sin(firstAngle)).toFloat())

            for (i in 1 until sides) {
                val x_i = x_c + r * cos(firstAngle + i *2* PI / sides)
                val y_i =y_c + r * sin(firstAngle + i * 2*PI / sides)
                path.lineTo(x_i.toFloat(), y_i.toFloat())

            }
            path.close()

            rotate(degrees = rotation) {
                scale(scaleX = scaleX, scaleY = scaleY) {
                    scale(scale = scale) {
                        drawPath(
                            path = path,
                            color = color,
                            style = style,
                            alpha = alpha,
                            colorFilter = colorFilter,
                            blendMode = blendMode
                        )
                    }
                }
            }
        }
        fill()
    }
}

@Composable
fun Polygon(modifier: Modifier = Modifier, sideLength: Dp, sides: Int = 3,
            center: Offset = Offset.Unspecified,
            rotation: Float = 0f,
            scale: Float = 1f,
            brush: Brush,
            scaleX: Float = 1f,
            scaleY: Float = 1f,
            @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f,
            style: DrawStyle = Fill,
            colorFilter: ColorFilter? = null,
            blendMode: BlendMode = DefaultBlendMode, fill: @Composable () -> Unit = {}) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier) {
            // design teh sate of the current value
            var computerCenter = if (center == Offset.Unspecified) Offset(size.width / 2, size.height / 2) else center
            val x_c = computerCenter.x
            val y_c = computerCenter.y
            val path = Path()
            path.moveTo(x_c, y_c)
            val r = sideLength.toPx() / (2* sin(PI / sides))
            val firstAngle = (sides - 2) * (PI / (2 * sides))
            path.moveTo((x_c + r * cos(firstAngle)).toFloat(), (y_c + r * sin(firstAngle)).toFloat())

            for (i in 1 until sides) {
                val x_i = x_c + r * cos(firstAngle + i *2* PI / sides)
                val y_i =y_c + r * sin(firstAngle + i * 2*PI / sides)
                path.lineTo(x_i.toFloat(), y_i.toFloat())
            }
            path.close()
            rotate(degrees = rotation) {
                scale(scaleX = scaleX, scaleY = scaleY) {
                    scale(scale = scale) {
                        drawPath(
                            path = path,
                            brush = brush,
                            style = style,
                            alpha = alpha,
                            colorFilter = colorFilter,
                            blendMode = blendMode
                        )
                    }

                }
            }
        }
        fill()
    }
}



@Preview
@Composable
private fun Preview() {
    Graph()
}