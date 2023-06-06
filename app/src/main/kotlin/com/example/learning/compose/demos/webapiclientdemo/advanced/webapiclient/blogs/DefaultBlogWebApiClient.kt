package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.blogs

import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons.ApiResponseWithBody
import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons.GenericJsonResponseParser
import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons.WebApiHttpClient
import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons.WebApiRequest
import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DefaultBlogWebApiClient : BlogWebApiClient {
    override suspend fun getBlogPosts(): ApiResponseWithBody<List<PostData>> {

        // List<T>の場合、JSONパーサーに型の情報を設定する必要がある
        val type = Types.newParameterizedType(List::class.java, PostData::class.java)
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<List<PostData>> = moshi.adapter(type)

        val httpClient = WebApiHttpClient()
        val parser = GenericJsonResponseParser(jsonAdapter)

        httpClient.requestWithResponseAsync(
            WebApiRequest(
                url = "https://jsonplaceholder.typicode.com/posts",
                method = "GET",
            ),
            parser
        ).let {
            return it
        }
    }
}
