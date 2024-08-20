package com.omdbapi.api.core

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ResponseTypeInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(createJsonTypeRequest(
            request = request,
            authenticatedUrl = createJsonRequestUrl(request)
        ))
    }

    private fun createJsonTypeRequest(request: Request, authenticatedUrl: HttpUrl): Request {
        val authenticatedRequest = request.newBuilder()
            .url(authenticatedUrl)
            .build()

        return authenticatedRequest
    }

    private fun createJsonRequestUrl(request: Request): HttpUrl {
        val parsed = request.url.toString().toHttpUrlOrNull()
            ?: throw IllegalArgumentException("Invalid URL: ${request.url}")

        return parsed.newBuilder()
            .addQueryParameter("r", "json")
            .build()
    }
}
