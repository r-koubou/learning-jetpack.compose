package com.example.learning.compose.demos.webapiclientdemo.simple

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.learning.compose.NaviScreen
import com.example.learning.compose.demos.webapiclientdemo.model.PostDataEntity
import com.example.learning.compose.demos.webapiclientdemo.webapiclient.WebApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimpleWebApiDemoScreen : NaviScreen
{
    val postEntity = PostDataEntity(mutableListOf())

    @Composable
    override fun Screen()
    {
        val service = WebApiService()

        val viewModel = PostDataViewModel(
            webApiClient = service,
            model = postEntity
        )

        PostList(viewModel)
    }

    @Composable
    private fun PostList(viewModel: PostDataViewModel)
    {
        val posts = viewModel.postDataList.observeAsState()
        var loading by remember { mutableStateOf(false) }

        val coroutine = rememberCoroutineScope()

        Column {
            Button(onClick = {
                loading = true
                coroutine.launch {
                    delay(2000) // ダミーでウェイト
                    viewModel.updatePostData()
                    loading = false
                }
            }){
                Text(text = "Update")
            }

            if (loading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ){
                    for (post in posts.value.orEmpty()) {
                        Text(text = post.title)
                    }
                }
            }
        }
    }
}
