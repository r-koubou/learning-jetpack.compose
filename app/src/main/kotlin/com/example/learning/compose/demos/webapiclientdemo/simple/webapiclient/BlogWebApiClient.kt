package com.example.learning.compose.demos.webapiclientdemo.simple.webapiclient

import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import retrofit2.Response
import retrofit2.http.GET

interface BlogWebApiClient
{
    @GET("posts")
    suspend fun getBlogPosts(): Response<List<PostData>>
}
