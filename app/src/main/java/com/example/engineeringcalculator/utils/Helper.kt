package com.example.engineeringcalculator.utils

import android.content.Context

fun pxToDp(context: Context, px: Double): Double {
    val density = context.resources.displayMetrics.density
    return px / density
}
