package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

open class WebApiHttpClient
{
    private val requestHeaders: MutableMap<String, String> = mutableMapOf()

    fun appendHttpRequestHeader(key: String, value: String)
        = requestHeaders.put(key, value)

    fun appendHttpRequestHeader(header: Map<String, String>)
            = requestHeaders.putAll(header)

    protected fun applyHttpRequestHeaders(connection: HttpURLConnection) {
        for (header in requestHeaders) {
            connection.setRequestProperty(header.key, header.value)
        }
    }

    suspend fun <TResponse> requestWithResponseAsync(
        request: WebApiRequest,
        parser: WebApiResponseParser<TResponse>
    ): ApiResponseWithBody<TResponse> {

        var response: ApiResponseWithBody<TResponse>? = null

        runBlocking {
            var connection: HttpURLConnection?

            val url = URL(request.url)
            val connectionTask = async {
                withContext(Dispatchers.IO) {
                    connection = url.openConnection() as HttpURLConnection
                    applyHttpRequestHeaders(connection!!)
                    connection!!.connect()
                    response = parser.parse(connection!!)
                }
            }
            connectionTask.await()
        }

        return response!!
    }

    suspend fun requestAsync(
        request: WebApiRequest
    ): ApiResponse {

        var response: ApiResponse? = null

        runBlocking {
            var connection: HttpURLConnection?

            val url = URL(request.url)
            val connectionTask = async {
                withContext(Dispatchers.IO) {
                    connection = url.openConnection() as HttpURLConnection
                    applyHttpRequestHeaders(connection!!)
                    connection!!.connect()
                    response = ApiResponse(connection!!.responseCode)
                }
            }
            connectionTask.await()
        }

        return response!!
    }
}
