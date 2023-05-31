package compose.demos.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import compose.NaviScreen

class TabDemoScreen : NaviScreen {

    @Composable
    override fun Screen() {

        var selectedIndex by remember { mutableStateOf(0) }

        // タブ部分のみで選択しているタブに対する表示コンテンツは
        // Composable を自分で並べてそう見えるように見せかける
        //
        // [横向きのタブ]
        // Column
        //   +-- TabRow
        //   +-- Composable
        //
        Column {

            TabRow(
                selectedTabIndex = selectedIndex,
                tabs = {
                    MyTab(0, selectedIndex) {
                        selectedIndex = it
                    }
                    MyTab(1, selectedIndex) {
                        selectedIndex = it
                    }
                }
            )

            when(selectedIndex) {
                0 -> {
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.Cyan)
                    ) {
                        Text("Tab 0 contents")
                    }
                }
                1 -> {
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.Green)
                    ) {
                        Text("Tab 1 contents")
                    }
                }
            }
        }
    }

    @Composable
    private fun MyTab(index: Int, selectedIndex: Int, onClicked: (Int) -> Unit) {
        Tab(
            selected = index == selectedIndex,
            onClick = {
                onClicked(index)
            }
        ) {
            Text(text = "Tab $index")
        }
    }
}
