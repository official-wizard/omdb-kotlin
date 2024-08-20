package com.omdbapi.api.core

import com.omdbapi.api.core.Credentials
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * This [Interceptor] takes care of the authentication process between requests
 */
class AuthenticatorInterceptor(private val credentials: Credentials): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(createAuthenticatedRequest(
            request = request,
            authenticatedUrl = createAuthenticatedUrl(request)
        ))
    }

    /**
     * Creates a new [Request] from the original [request] using the [authenticatedUrl] provided
     */
    private fun createAuthenticatedRequest(request: Request, authenticatedUrl: HttpUrl): Request {
        val authenticatedRequest = request.newBuilder()
            .url(authenticatedUrl)
            .build()

        return authenticatedRequest
    }

    /**
     * Creates a new [HttpUrl] with [credentials] appended to the query
     *
     * OMDbApi.com uses query parameters for the authentication process
     */
    private fun createAuthenticatedUrl(request: Request): HttpUrl {
        val parsed = request.url.toString().toHttpUrlOrNull()
            ?: throw IllegalArgumentException("Invalid URL: ${request.url}")

        return parsed.newBuilder()
            .addQueryParameter("apikey", credentials.apiKey)
            .build()
    }
}
