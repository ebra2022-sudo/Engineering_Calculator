package com.example.engineeringcalculator.screens


import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engineeringcalculator.R
import com.example.engineeringcalculator.ui.theme.EngineeringCalculatorTheme
import com.example.engineeringcalculator.ui.theme.localCustomColorScheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicCalculator(
    onNavigationIconClick: () -> Unit = {}

) {
    val colors = localCustomColorScheme.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClick ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = colors.iconButton)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = colors.basicUpperPanelBackground))
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colors.basicScreenBackground)
            .padding(it),
            verticalArrangement = Arrangement.SpaceBetween) {
            var expression by remember { mutableStateOf("") }
            BasicUpperPanel(expression = expression)
            BasicLowerPanel(
                onButtonClick = {
                    if (it == "C") expression = ""
                    else if (expression.length <= 20) expression = expression.plus(it)

                }, onClearClick = { expression = expression.dropLast(1) })
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
                    label = { Text(text = screen.name, color = if (currentScreen == screen) Color.Green else Color.Black) },
                    selected = false,
                    onClick = { onItemClick(screen) },
                    shape = RoundedCornerShape(16.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.LightGray,
                        unselectedTextColor = Color.Green,
                    )
                )
            }
        }
    }
}





//Upper panel
@Composable
fun BasicUpperPanel(expression: String = "", result: String = "") {
    val colors = localCustomColorScheme.current
    val shadowColor = colors.basicUpperPanelShadow // Semi-transparent black
    val shadowOffset = Offset(0f, 4f) // Shadow direction (left and up)
    val shadowRadius = 50f// Shadow blur radius

    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.33f)
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
        shape = RoundedCornerShape(bottomStart = 37.38.dp, bottomEnd = 37.38.dp),
        colors = CardDefaults.cardColors(containerColor = colors.basicUpperPanelBackground),
        ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), text = expression, textAlign = TextAlign.Right, fontSize = 28.sp)
            Text(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), text = result, textAlign = TextAlign.Right, fontSize = 28.sp)

        }

    }
}

@Composable
fun BasicLowerPanel(onHistoryClick: () -> Unit = {}, onClearClick: () -> Unit = {}, onButtonClick: (String) -> Unit = {}) {
    val keys = arrayOf("C", "( )", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=")
    val colors = localCustomColorScheme.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.SpaceBetween) {
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.iconButton,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(R.drawable.history__1_),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = colors.iconButton
                )
            }

            IconButton(onClick = onClearClick) {
                Icon(
                    painter = painterResource(R.drawable.clear_symbol_icon_clear_icon_11553466179ohmvqvonvz),
                    contentDescription = null,
                    modifier = Modifier.size(width = 27.dp, height = 24.dp),
                    tint = colors.iconButton
                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))
        BasicKeyBoard(keys = keys, onButtonClick = onButtonClick)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicKeyBoard(keys: Array<String>, onButtonClick: (String) -> Unit = {}) {
    FlowRow (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = 4, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        keys.forEachIndexed {
                index, key->
            BasicButtonItem(key = key, index = index, onClick = onButtonClick)
        }
    }
}



@Composable
fun BasicButtonItem(modifier: Modifier = Modifier, key: String, index: Int, onClick: (String) -> Unit) {
    val colors = localCustomColorScheme.current

    val shadowColor = colors.basicUpperPanelShadow // Semi-transparent black
    val shadowOffset = Offset(0f, 7f) // Shadow direction (left and up)
    val shadowRadius = 8f
    Card(modifier = Modifier
        .size(75.dp)
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
        }
        .clickable(onClick = { onClick(key) }), shape = CircleShape, elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = when(index) {
            19 -> colors.basicOperatorButtonKey
            0,1,2,3,7,11, 15-> colors.basicOperatorButtonBackground
            else -> colors.basicNumberButtonBackground

        }, contentColor = when(index) {
            0 -> colors.cancel
            19 -> colors.equalKey
            1,2,3,7,11,15 -> colors.basicOperatorButtonKey
            else -> colors.basicNumberButtonKey

        })) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = key, fontSize = 40.sp, style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_light))))
        }
    }
}



@Composable
fun OrientationScreen(
    isLandscape: Boolean,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val activity = rememberUpdatedState(context as Activity)

    DisposableEffect(isLandscape) {
        val requestedOrientation = if (isLandscape) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        activity.value.requestedOrientation = requestedOrientation

        onDispose {
            // Reset to sensor orientation on exit
            activity.value.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    content()
}




@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun PreviewLightMode() {
    EngineeringCalculatorTheme {
        //UpperPanel()
        //ButtonItem()
        //BasicKeyBoard()
        BasicCalculator()
    }
}


@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun PreviewDarkMode() {
    EngineeringCalculatorTheme (darkTheme = true){
        //UpperPanel()
        //ButtonItem()
        //BasicKeyBoard()
        BasicCalculator()
    }
}

