package com.example.engineeringcalculator.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.engineeringcalculator.ui.theme.EngineeringCalculatorTheme
import kotlinx.coroutines.launch
import com.example.engineeringcalculator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scientific(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.scrim)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)) {

        }
    }
}

@Composable
fun ScientificUpperPanel(modifier: Modifier = Modifier) {
    
}

@Composable
fun ScientificLowerPanel(modifier: Modifier = Modifier) {

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScientificKeyBoard(modifier: Modifier = Modifier) {
    val keys = arrayOf("rad","\u221A","\u221B","5","6","7","8","9","0","1","2","3","4","5","6","7","8","9","0","1","2","3","4","5","6","7","8","9","0")
    FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 8, verticalArrangement = Arrangement.spacedBy(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
        IconButton(
            modifier = Modifier.size(width = 60.dp, height = 45.dp).clip(MaterialTheme.shapes.medium).background(Color(0xFFEAEAEA)),
            onClick = {}) {
            Icon(painter = painterResource(R.drawable.shift_icon), contentDescription = "Shift", tint = MaterialTheme.colorScheme.onPrimary)

        }
        keys.forEachIndexed { index, key ->
            ScientificButtonItem(modifier = Modifier, key = key, index = index)
        }

    }
    
}

@Composable
fun ScientificButtonItem(modifier: Modifier = Modifier, key: String, index: Int) {
    Card(modifier = Modifier.size(width = 60.dp, height = 45.dp).then(modifier),
        shape = MaterialTheme.shapes.medium,) {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center) {
            Text(text = key)
        }
    }

    
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape", showBackground = true)
@Composable
private fun Preview() {
    EngineeringCalculatorTheme() {
        //Scientific()
        //ScientificButtonItem(key = "1", index = 1)
        ScientificKeyBoard()
    }
}

