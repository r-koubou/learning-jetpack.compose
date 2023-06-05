package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.net.HttpURLConnection
import kotlin.reflect.KClass

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
