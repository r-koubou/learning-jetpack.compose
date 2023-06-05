package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient

import com.example.learning.compose.demos.webapiclientdemo.model.PostData

interface BlogWebApiClient  {
    suspend fun getBlogPosts(): ApiResponseWithBody<List<PostData>>
}
