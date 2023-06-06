package com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.commons

class WebApiRequest(
    val url: String,
    val query: Map<String, String> = emptyMap(),
    val method: String = "GET",
) {
    fun getUrlWithQuery(): String {
        if (query.isEmpty()) {
            return url
        }

        val stringBuilder = StringBuilder(url)

        stringBuilder.append("?")
        return buildQuery(stringBuilder).toString()
    }

    fun getUrlQuery(): String {
        if (query.isEmpty()) {
            return ""
        }
        return buildQuery(StringBuilder()).toString()
    }

    private fun buildQuery(queryBuilder: StringBuilder): StringBuilder {
        if (query.isEmpty()) {
            return queryBuilder
        }

        queryBuilder.append("?")

        for(entry in query.entries) {
            queryBuilder.append("${entry.key}=${entry.value}")
            if(entry != query.entries.last()) {
                queryBuilder.append("&")
            }
        }

        return queryBuilder
    }
}
