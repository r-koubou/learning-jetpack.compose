package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons

import com.squareup.moshi.JsonAdapter
import java.io.InputStream
import java.net.HttpURLConnection

class GenericJsonResponseParser<TResponse:Any>(
    private val jsonAdapter: JsonAdapter<TResponse>
) : WebApiResponseParser<TResponse>
{
    override fun parse(connection: HttpURLConnection): ApiResponseWithBody<TResponse>
    {
        val statusCode = connection.responseCode
        var inputStream: InputStream? = null

        try {
            if (!connection.responseCode.httpSucceeded()) {
                return ApiResponseWithBody(statusCode)
            }

            inputStream = connection.inputStream
            val json = inputStream.bufferedReader().use { it.readText() }
            val body = jsonAdapter.fromJson(json)

            return ApiResponseWithBody(statusCode, body)

        } catch (ex: Exception) {
            return ApiResponseWithBody()
        } finally {
            try { inputStream?.close() } catch (ex: Exception) {/*ignore*/}
        }
    }
}
