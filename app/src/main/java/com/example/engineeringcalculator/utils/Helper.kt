package com.example.engineeringcalculator.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun pxToDp(context: Context, px: Double): Dp {
    val density = context.resources.displayMetrics.density
    return (px / density).dp
}
