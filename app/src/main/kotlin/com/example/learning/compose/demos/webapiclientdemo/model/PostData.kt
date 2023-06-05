package com.example.learning.compose.demos.webapiclientdemo.model

import com.squareup.moshi.JsonClass

data class PostData (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
