package com.example.learning.compose.demos.webapiclientdemo.webapiclient

import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class WebApiService() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        // JSON デシリアライザの指定
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        ))
        // HTTPクライアントの指定
        .client(
            OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .build()
    private val client: BlogWebApiClient = retrofit.create(BlogWebApiClient::class.java)

    suspend fun getBlogPosts(): List<PostData> {

        val response = client.getBlogPosts()

        if(response.isSuccessful) {
            return response.body() ?: emptyList()
        }

        throw HttpException(response)
    }
}
