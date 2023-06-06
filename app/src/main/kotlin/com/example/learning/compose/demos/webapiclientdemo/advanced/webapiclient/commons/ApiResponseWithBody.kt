package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons

class ApiResponseWithBody<TResponse>(
    val statusCode : Int = 0,
    val body : TResponse? = null
)
{}
