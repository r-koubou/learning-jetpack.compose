package com.example.learning.compose.demos.webapiclientdemo.simple.webapiclient

import com.example.learning.compose.demos.webapiclientdemo.model.PostData

interface WebApiService
{
    suspend fun getBlogPosts(): List<PostData>
}
