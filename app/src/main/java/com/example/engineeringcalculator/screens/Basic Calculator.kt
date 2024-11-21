package com.example.engineeringcalculator.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engineeringcalculator.R
import com.example.engineeringcalculator.ui.theme.EngineeringCalculatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicCalculator(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.scrim)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary))


        }

    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(it), verticalArrangement = Arrangement.SpaceBetween) {
            UpperPanel()
            LowerPanel()
        }


    }

}
// sample current value


@Composable
fun DrawerContent(onMenuItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Basic",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onMenuItemClick("") }
        )
        Text(
            text = "Settings",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onMenuItemClick("Settings") }
        )

        Text(
            text = "Profile",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onMenuItemClick("Profile") }
        )

    }
}

@Composable
fun UpperPanel(modifier: Modifier = Modifier) {
    val shadowColor = MaterialTheme.colorScheme.scrim // Semi-transparent black
    val shadowOffset = Offset(0f, 4f) // Shadow direction (left and up)
    val shadowRadius = 50f // Shadow blur radius
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
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
            // sample of the current value of the state of
            //
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

        ) {

    }
}

@Composable
fun LowerPanel(modifier: Modifier = Modifier) {
    BasicKeyBoard()
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicKeyBoard(modifier: Modifier = Modifier) {
    val keys = arrayOf("C", "( )", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=")
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.SpaceBetween) {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.scrim, modifier = Modifier.padding(horizontal = 16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = {}) { 
                Icon(painter = painterResource(R.drawable.history__1_), contentDescription = null, modifier= Modifier.size(24.dp), tint = MaterialTheme.colorScheme.scrim)
            }
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.clear_symbol_icon_clear_icon_11553466179ohmvqvonvz), contentDescription = null, modifier= Modifier.size(width = 27.dp, height = 24.dp), tint = MaterialTheme.colorScheme.scrim)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        FlowRow (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 4, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            keys.forEachIndexed {
                    index, key->
                ButtonItem(key = key, index = index)
            }
        }
    }
}



@Composable
fun ButtonItem(modifier: Modifier = Modifier, key: String, index: Int) {
    val shadowColor = MaterialTheme.colorScheme.scrim // Semi-transparent black
    val shadowOffset = Offset(0f, 5f) // Shadow direction (left and up)
    val shadowRadius = 8f
    Card(modifier = Modifier.size(75.dp).drawBehind {
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
    }, shape = CircleShape, elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = when(index) {
            19 -> MaterialTheme.colorScheme.onSecondary
            0,1,2,3,7,11, 15-> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.primary

        }, contentColor = when(index) {
            0 -> MaterialTheme.colorScheme.tertiary
            19 -> Color.White
            1,2,3,7,11,15 -> MaterialTheme.colorScheme.onSecondary
            else -> MaterialTheme.colorScheme.onPrimary

        })) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = key, fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.inter_light)))
        }
    }
}
// sample
// sampleof the  current value of the  state f the current value


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

