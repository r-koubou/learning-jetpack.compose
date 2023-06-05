package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient

import java.net.HttpURLConnection

fun Int.httpSucceeded(): Boolean = this in 200..299

interface WebApiResponseParser<TResponse> {
    fun parse(connection: HttpURLConnection): ApiResponseWithBody<TResponse>
}

