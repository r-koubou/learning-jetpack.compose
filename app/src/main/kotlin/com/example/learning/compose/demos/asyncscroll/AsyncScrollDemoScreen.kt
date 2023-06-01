package com.example.learning.compose.demos.asyncscroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning.compose.NaviScreen

class AsyncScrollDemoScreen : NaviScreen {

    @Composable
    override fun Screen() {

        val profiles = remember { mutableStateListOf<ProfileModel>() }

        Column {
            Row {
                AddButton {
                    val currentListSize = profiles.size
                    for (i in 1..10) {
                        val id = currentListSize + i
                        profiles += ProfileModel(
                            name = "Name $id",
                            description = "Description of $id",
                            webUrl = "http://$id",
                            thumbnailPath = "https://picsum.photos/300")
                    }
                }
                ClearButton()
            }
            ProfileListComposable(profiles)
        }
    }

    @Composable
    private fun AddButton(onCLicked: ()->Unit = {}) {
        Button(onClick = onCLicked) {
            Text(text = "Add")
        }
    }

    @Composable
    private fun ClearButton(onCLicked: ()->Unit = {}) {
        Button(onClick = onCLicked) {
            Text(text = "Clear")
        }
    }

    @Composable
    private fun ProfileListComposable(profiles: MutableList<ProfileModel>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(profiles) {
                ProfileComposable(it)
            }
        }
    }
}

@Preview(name = "TabDemoScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_AsyncScrollDemoScreen() {
    AsyncScrollDemoScreen().Screen()
}
