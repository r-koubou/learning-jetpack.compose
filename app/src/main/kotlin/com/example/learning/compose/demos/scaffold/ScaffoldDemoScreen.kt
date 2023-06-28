package com.example.learning.compose.demos.scaffold

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning.compose.NaviScreen

class ScaffoldDemoScreen : NaviScreen
{
    @Composable
    override fun Screen()
    {
        val bgColor = Color(0xFFC8E6C9)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

        val addedItems = remember { mutableStateListOf<String>()}
        var addedCount by remember { mutableStateOf(0) }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(backgroundColor = bgColor) {
                    Text(text = "Scaffold Demo Top bar")
                }
            },
            floatingActionButton = {
               FloatingActionButton(onClick = {
                   addedItems.add("Item $addedCount")
                   addedCount++
                   Log.d("ScaffoldDemoScreen", "FloatingActionButton.onClick ${addedItems.size}")
               }){
                   Text(text = "+")
               }
            },
            content = {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = it.calculateBottomPadding())
                ) {
                    Text(text = "padding value: $it", fontWeight = FontWeight.Bold)
                    addedItems.forEach { x ->
                        Text(text = x)
                    }
                }
            },
            bottomBar = {
                BottomAppBar(backgroundColor = bgColor){
                    Text(text = "Scaffold Demo Bottom bar")
                }
            }
        )
    }
}

@Preview(name = "ScaffoldDemoScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_ScaffoldDemoScreen() {
    ScaffoldDemoScreen().Screen()
}
