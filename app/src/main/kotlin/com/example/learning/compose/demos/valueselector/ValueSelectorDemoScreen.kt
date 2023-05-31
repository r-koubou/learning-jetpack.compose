package com.example.learning.compose.demos.valueselector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning.compose.NaviScreen

class ValueSelectorDemoScreen : NaviScreen {

    @Composable
    override fun Screen() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Switch(
                checked = true,
                onCheckedChange = { }
            )
        }
    }
}


@Preview(name = "ValueSelectorDemoScreen")
@Composable
private fun Preview_ValueSelectorDemoScreen() {
    ValueSelectorDemoScreen().Screen()
}
