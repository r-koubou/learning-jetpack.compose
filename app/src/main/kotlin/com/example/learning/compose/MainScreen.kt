package com.example.learning.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learning.compose.demos.asyncscroll.AsyncScrollDemoScreen
import com.example.learning.compose.demos.localization.LocalizationDemoScreen
import com.example.learning.compose.demos.progress.ProgressDemoScreen
import com.example.learning.compose.demos.tab.TabDemoScreen
import com.example.learning.compose.demos.textfield.TextFieldDemoScreen
import com.example.learning.compose.demos.valueselector.ValueSelectorDemoScreen
import com.example.learning.compose.demos.webapiclientdemo.advanced.AdvancedWebApiDemoScreen
import com.example.learning.compose.demos.webapiclientdemo.simple.SimpleWebApiDemoScreen
import com.example.learning.compose.ui.theme.LearningComposeTheme

class MainScreen : NaviScreen
{
    @Composable
    override fun Screen() {

        var currentScreen by remember { mutableStateOf(Screen.MainMenu) }

        val naviController = rememberNavController()
        naviController.addOnDestinationChangedListener() { _, destination, _ ->
            currentScreen = Screen.valueOf(destination.route ?: Screen.MainMenu.name)
        }

        LearningComposeTheme {

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Column {

                    if (currentScreen != Screen.MainMenu) {
                        NavigationTitleBar(title = currentScreen.name) {
                            naviController.popBackStack()
                        }
                    }

                    NavHost(
                        navController = naviController,
                        startDestination = Screen.MainMenu.name
                    ) {
                        composable(Screen.MainMenu.name) {
                            ContentList(naviController = naviController) {
                                currentScreen = it
                            }
                        }
                        composable(Screen.AsyncScrollDemo.name) {
                            AsyncScrollDemoScreen().Screen()
                        }
                        composable(Screen.TabDemo.name) {
                            TabDemoScreen().Screen()
                        }
                        composable(Screen.TextFieldDemo.name) {
                            TextFieldDemoScreen().Screen()
                        }
                        composable(Screen.ValueSelectorDemo.name) {
                            ValueSelectorDemoScreen().Screen()
                        }
                        composable(Screen.ProgressDemo.name) {
                            ProgressDemoScreen().Screen()
                        }
                        composable(Screen.LocalizationDemo.name) {
                            LocalizationDemoScreen().Screen()
                        }
                        composable(Screen.SimpleWebApiDemo.name) {
                            SimpleWebApiDemoScreen().Screen()
                        }
                        composable(Screen.AdvancedWebApiDemo.name) {
                            AdvancedWebApiDemoScreen().Screen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationTitleBar(title: String, onBackClicked: () -> Unit = {})
    {
        TopAppBar(
            title = {
                Text(title)
            },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
            actions = {
                // 任意のボタン類などの配置
            }
        )
    }

    @Composable
    private fun ContentList(
        naviController: NavHostController,
        onCurrentScreenChanged: (Screen) -> Unit
    ) {

        Column {
            DemoLinkButton(naviController, Screen.AsyncScrollDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.TabDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.TextFieldDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.ValueSelectorDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.ProgressDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.LocalizationDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.SimpleWebApiDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.AdvancedWebApiDemo, onCurrentScreenChanged)
        }
    }

    @Composable
    private fun DemoLinkButton(
        naviController: NavHostController,
        nextScreen: Screen,
        onCurrentScreenChanged: (Screen) -> Unit
    )
    {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onCurrentScreenChanged(nextScreen)
                naviController.navigate(nextScreen.name)
            }) {
            Text(
                text = nextScreen.name,
            )
        }
    }
}


@Preview(name = "MainScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_MainScreen() {
    MainScreen().Screen()
}
