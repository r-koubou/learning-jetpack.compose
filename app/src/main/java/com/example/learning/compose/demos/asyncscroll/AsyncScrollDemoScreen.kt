package com.example.learning.compose.demos.asyncscroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.example.learning.compose.NaviScreen

class AsyncScrollDemoScreen : NaviScreen {
    // SwiftUIでいう
    // @State private var profiles = [ProfileModel]
    private var profiles = mutableStateListOf<ProfileModel>()

    @Composable
    override fun Screen() {
        Column {
            Row {
                AddButton()
                ClearButton()
            }
            ProfileListComposable()
        }
    }

    @Composable
    private fun AddButton() {
        Button(onClick = {
            val currentListSize = profiles.size
            for (i in 1..10) {
                val id = currentListSize + i
                profiles += ProfileModel(
                    name = "Name $id",
                    description = "Description of $id",
                    webUrl = "http://$id",
                    thumbnailPath = "https://picsum.photos/300")
            }
        }) {
            Text(text = "Add")
        }
    }

    @Composable
    private fun ClearButton() {
        Button(onClick = {
            profiles.clear()
        }) {
            Text(text = "Clear")
        }
    }

    @Composable
    private fun ProfileListComposable() {
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
