package com.example.learning.compose.demos.valueselector

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learning.compose.NaviScreen
import com.example.learning.compose.percent
import java.text.DecimalFormat

class ValueSelectorDemoScreen : NaviScreen {

    @Composable
    override fun Screen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            SwitchDemo()
            SliderDemo()
            DropdownMenuDemo()
        }
    }

    private val titleTextAlignment = Alignment.Center
    private val padding = 24f.dp

    // 各UI配置用のルート要素
    @Composable
    private fun MyBox(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit)
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .then(modifier)
        ) {
            content()
        }
    }

    @Composable
    private fun SwitchDemo()
    {
        var switchValue by remember { mutableStateOf(false) }

        MyBox {
            Text(
                text = "Switch",
                modifier = Modifier.align(titleTextAlignment)
            )
            Switch(
                checked = switchValue,
                onCheckedChange = {
                    switchValue = it
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }

    @Composable
    private fun SliderDemo()
    {
        var sliderValue by remember { mutableStateOf(0f) }

        MyBox {
            Row {
                Text(
                    text = DecimalFormat("#.###").format(sliderValue),
                    modifier = Modifier
                        .weight(20f.percent)
                        .align(Alignment.CenterVertically)
                )
                Slider(value = sliderValue, onValueChange = {
                    sliderValue = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .weight(80f.percent))
            }
        }
    }

    @Composable
    private fun DropdownMenuDemo()
    {
        val menuItems = listOf("Item1", "Item2", "Item3")
        val disableValue = "item2"

        var expanded by remember { mutableStateOf(false)}
        var selectedIndex by remember { mutableStateOf(0) }

        MyBox(
            modifier = Modifier
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Dropdown Menu",
                    modifier = Modifier
                        .weight(30f.percent)
                        .align(Alignment.CenterVertically)
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(70f.percent),
                    onClick = {
                        expanded = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    menuItems.forEachIndexed { index, x ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded = false
                        }) {
                            if (x == disableValue) {
                                Text(text = x, color = Color.Gray)
                            } else {
                                Text(text = x)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(name = "ValueSelectorDemoScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_ValueSelectorDemoScreen() {
    ValueSelectorDemoScreen().Screen()
}
