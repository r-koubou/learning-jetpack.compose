package com.example.learning.compose.demos.webapiclientdemo.simple.webapiclient

import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import retrofit2.Response
import retrofit2.http.GET

// Retrofit に BlogWebApiClient::class.java を渡すだけで実行可能なので実装クラスは無い
interface BlogWebApiClient
{
    @GET("posts")
    suspend fun getBlogPosts(): Response<List<PostData>>
}
