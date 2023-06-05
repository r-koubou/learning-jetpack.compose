package com.example.learning.compose.demos.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learning.compose.NaviScreen
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.delay

class ProgressDemoScreen : NaviScreen
{
    @Composable
    override fun Screen()
    {
        var progress by remember { mutableStateOf(0f) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8f.dp),
        ){

            Text(CircularProgressIndicator::class.java.simpleName)
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(8f.dp))

            Text(LinearProgressIndicator::class.java.simpleName)
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .padding(12f.dp)
                    .align(Alignment.CenterHorizontally)
            )

        }

        // タイマーイベント
        LaunchedEffect(key1 = true){
            while (true){
                progress += 0.01f
                if (progress > 1f) progress = 0f

                // Sleepではない、コルーチン的なウェイト
                delay(50)
            }
        }
    }
}


@Preview(name = "MainScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_ProgressDemoScreen() {
    ProgressDemoScreen().Screen()
}
