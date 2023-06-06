package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.blogs

import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons.ApiResponseWithBody
import com.example.learning.compose.demos.webapiclientdemo.model.PostData

interface BlogWebApiClient  {
    suspend fun getBlogPosts(): ApiResponseWithBody<List<PostData>>
}
