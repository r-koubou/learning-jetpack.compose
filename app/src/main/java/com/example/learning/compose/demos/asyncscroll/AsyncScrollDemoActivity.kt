package com.example.learning.compose.demos.asyncscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.example.learning.compose.demos.asyncscroll.ui.theme.LearningComposeTheme

class AsyncScrollDemoActivity : ComponentActivity() {
    // SwiftUIでいう
    // @State private var profiles = [ProfileModel]
    private var profiles = mutableStateListOf<ProfileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            AddButton()
                            ClearButton()
                        }
                        ProfileListComposable()
                    }
                }
            }
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
