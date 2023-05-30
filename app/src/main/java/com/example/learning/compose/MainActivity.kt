package com.example.learning.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learning.compose.demos.asyncscroll.AsyncScrollDemoScreen
import com.example.learning.compose.demos.tab.TabDemoScreen
import com.example.learning.compose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {

    enum class Screen {
        MainMenu,
        AsyncScrollDemo,
        TabDemo,
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

    @Composable
    private fun MainScreen() {

        var currentScreen by remember { mutableStateOf(Screen.MainMenu) }

        val naviController = rememberNavController()
        naviController.addOnDestinationChangedListener() { _, destination, _ ->
            currentScreen = Screen.valueOf(destination.route ?: Screen.MainMenu.name)
        }

        LearningComposeTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column {

                    if(currentScreen != Screen.MainMenu)
                    {
                        BackNavigation(naviController = naviController)
                    }

                    NavHost(
                        navController = naviController,
                        startDestination = Screen.MainMenu.name
                    ) {
                        composable(Screen.MainMenu.name) {
                            ContentList(naviController = naviController){
                                currentScreen = it
                            }
                        }
                        composable(Screen.AsyncScrollDemo.name) {
                            AsyncScrollDemoScreen().Screen()
                        }
                        composable(Screen.TabDemo.name) {
                            TabDemoScreen().Screen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ContentList(
        naviController: NavHostController,
        onCurrentScreenChanged: (Screen) -> Unit
    ) {

        Column {
            DemoLinkButton(naviController, Screen.AsyncScrollDemo, onCurrentScreenChanged)
            DemoLinkButton(naviController, Screen.TabDemo, onCurrentScreenChanged)
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
