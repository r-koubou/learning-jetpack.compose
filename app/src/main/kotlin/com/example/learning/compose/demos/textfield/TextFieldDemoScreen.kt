package com.example.learning.compose.demos.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learning.compose.NaviScreen

class TextFieldDemoScreen : NaviScreen
{
    @Composable
    override fun Screen()
    {
        var textFieldText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ){

                Button(onClick = {
                    textFieldText = ""
                }) {
                    Text("Clear")
                }

                Button(onClick = {
                    textFieldText += String((0..1000).map { ('a'..'z').random() }.toCharArray())
                }) {
                    Text("Random")
                }
            }

            Text("Single line text field")
            TextField(
                value = textFieldText,
                onValueChange = {
                    textFieldText = it
                },
                singleLine = true,
                label = { Text("text...") }
            )

            Text("Output")

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(textFieldText,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "TextFieldDemoScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_TextFieldDemoScreen() {
    TextFieldDemoScreen().Screen()
}
