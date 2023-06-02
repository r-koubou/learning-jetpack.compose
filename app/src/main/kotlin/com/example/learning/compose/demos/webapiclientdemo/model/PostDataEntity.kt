package com.example.learning.compose.demos.webapiclientdemo.model

data class PostDataEntity (
    private val postDataList: MutableList<PostData> = mutableListOf()
) : MutableList<PostData> by postDataList
