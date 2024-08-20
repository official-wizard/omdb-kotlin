package com.omdbapi.api.core

import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.omdbapi.api.core.Credentials
import com.omdbapi.api.core.ResponseTypeInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class CoreClient(credentials: Credentials, baseUrl: String, debugging: Boolean = false) {

    // custom [OkHttpClient] client to add an authentication interceptor
    var httpClient: OkHttpClient = OkHttpClient.Builder().apply {

        // append query parameter to add the API Key to our requests
        addInterceptor(AuthenticatorInterceptor(credentials))

        // append query parameter to make responses come back as JSON
        addInterceptor(ResponseTypeInterceptor())

        if (debugging) {
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }
    }.build()

    // custom [Retrofit] client to add custom [OkHttpClient] and add Gson (JSON) support
    val omdbClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .baseUrl(baseUrl)
        .client(httpClient)
        .build()
}
