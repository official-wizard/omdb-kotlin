package com.omdbapi.api

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.omdbapi.api.data.Plot
import com.omdbapi.api.data.QueryType
import com.omdbapi.api.data.pojo.Error
import com.omdbapi.api.data.pojo.Search
import com.omdbapi.api.data.pojo.SearchList
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbInterface {

    /**
     * This [searchList] function will search OMDB for a list of movies by the provided [title] and query options
     */
    @Mock @MockResponse(body = "/v1/search/SearchList.json")
    @GET("/")
    suspend fun searchList(
        @Query("s") title: String,
        @Query("type") type: QueryType? = null,
        @Query("y") yearOfRelease: Int? = null,
        @Query("page") page: Int = 1,
        @Query("callback") callback: String? = null,
        @Query("v") apiVersion: Int = 1
    ): NetworkResponse<SearchList.Response, Error.Response>

    /**
     * This [searchTitle] function will search OMDB for the specified [title] or [imdbId]
     * although both parameters are marked as nullable/optional, at least one of the option is required!
     */
    @Mock @MockResponse(body = "/v1/search/SearchTitle.json")
    @GET("/")
    suspend fun searchTitle(
        @Query("t") title: String? = null,
        @Query("i") imdbId: String? = null,
        @Query("type") type: QueryType? = null,
        @Query("y") yearOfRelease: Int? = null,
        @Query("plot") plot: Plot? = null,
        @Query("callback") callback: String? = null,
        @Query("v") apiVersion: Int = 1
    ): NetworkResponse<Search.Response, Error.Response>
}