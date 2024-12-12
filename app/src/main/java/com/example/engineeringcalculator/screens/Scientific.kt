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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engineeringcalculator.R
import com.example.engineeringcalculator.ui.theme.EngineeringCalculatorTheme
import com.example.engineeringcalculator.ui.theme.localCustomColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScientificScreen(
    onNavigationIconClick: () -> Unit = {}
) {
    // design the  sta the
    var expression by remember { mutableStateOf("") }
    val colors = localCustomColorScheme.current
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(55.dp),
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = colors.scientificIconButton)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = colors.scientificBackground)
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(colors.scientificBackground)
            .padding(horizontal = 20.dp)) {
            ScientificUpperPanel(expression = expression)
            ScientificLowerPanel(onClearClick = {expression = ""}, onButtonClick = {expression = expression.plus(it)})

        }
    }
}


@Composable
fun ScientificUpperPanel(expression: String = "") {
    Box(modifier = Modifier
        .fillMaxWidth().fillMaxHeight(0.12f), contentAlignment = Alignment.TopEnd) {
        Text(text = expression, fontSize = 16.sp )
    }
}
@Composable
fun ScientificLowerPanel(onHistoryClick: () -> Unit = {}, onClearClick: () -> Unit = {}, onButtonClick: (String) -> Unit = {}) {
    val keys = arrayOf("rad","\u221A","\u221B","C","( )","%","÷","sin","cos","tan","sec",
        "7","8","9","×","sinh","cosh","tanh","csc","4","5","6","-","log","ln","log b x",
        "cot","1","2","3","+","x!","c(n,r)","p(n,r)","cons.","+/-","0",".","=")
    val colors = localCustomColorScheme.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 8.dp)) {
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.scientificIconButton,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(R.drawable.history__1_),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = colors.scientificIconButton
                )
            }

            IconButton(onClick = onClearClick) {
                Icon(
                    painter = painterResource(R.drawable.clear_symbol_icon_clear_icon_11553466179ohmvqvonvz),
                    contentDescription = null,
                    modifier = Modifier.size(width = 23.dp, height = 24.dp),
                    tint = colors.scientificIconButton
                )
            }

        }

        Spacer(modifier = Modifier.height(5.dp))
        ScientificKeyBoard(keys = keys, onButtonClick = onButtonClick)
    }
}

// Design

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScientificKeyBoard(keys: Array<String>, onButtonClick: (String) -> Unit = {}, onShiftClick: () -> Unit = {}) {
    val colors = localCustomColorScheme.current
    FlowRow(modifier = Modifier
        .fillMaxWidth(), maxItemsInEachRow = 8, verticalArrangement = Arrangement.spacedBy(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
        IconButton(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(colors.scientificNumberButtonBackground).size(width = 60.dp, height = 36.dp),
            onClick = onShiftClick) {
            Icon(painter = painterResource(R.drawable.shift_icon), contentDescription = "Shift", tint = colors.scientificNumberButtonKey, modifier = Modifier.size(20.dp))

        }
        keys.forEachIndexed { index, key ->
            ScientificButtonItem(modifier = Modifier, key = key, index = index, onButtonClick = onButtonClick)
        }
    }
}

// design teh
@Composable
fun ScientificButtonItem(modifier: Modifier = Modifier, key: String, index: Int, onButtonClick: (String) -> Unit = {}) {
    val colors = localCustomColorScheme.current
    Card(
        modifier = Modifier
            .size(width = 60.dp, height = 36.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    when (index) {
                        3, 4, 5, 6, 14, 22, 30 -> colors.basicOperatorButtonBackground
                        38 -> colors.basicOperatorButtonKey
                        else -> colors.scientificNumberButtonBackground
                    }
                )
                .clickable(onClick = { onButtonClick(key) }), contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (key.split(" ").size  == 2 && key[0] != '(')
                    buildAnnotatedString{
                        val subStrings = key.split(" ")
                        withStyle(style = SpanStyle()) {
                            append(subStrings[0])
                        }
                        withStyle(style = SpanStyle(baselineShift = BaselineShift.Superscript)) {
                            append(subStrings[1])
                        }
                    }
                else if (key.split(" ").size == 3) buildAnnotatedString{
                    val subStrings = key.split(" ")
                    withStyle(style = SpanStyle()) {
                        append(subStrings[0])
                    }
                    withStyle(style = SpanStyle(baselineShift = BaselineShift.Subscript)) {
                        append(subStrings[1])
                    }
                    withStyle(style = SpanStyle(baselineShift = BaselineShift.Superscript)) {
                        append(subStrings[2])
                    }
                }
                else
                    buildAnnotatedString { append(key) },
            color = when (index) {
                    3 -> colors.cancel
                    4, 5, 6, 14, 22, 30 -> colors.basicOperatorButtonKey
                    38 -> colors.equalKey
                    else -> colors.scientificNumberButtonKey
                },
                fontSize = 19.sp
            )
        }
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape", showBackground = true)
@Composable
private fun Preview() {
    EngineeringCalculatorTheme {
        //Scientific()
        //ScientificButtonItem(key = "1", index = 1)
        //ScientificKeyBoard()
        //ScientificScreen()
    }
}

