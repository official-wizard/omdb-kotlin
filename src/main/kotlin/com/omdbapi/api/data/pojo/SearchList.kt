package com.omdbapi.api.data.pojo

import com.google.gson.annotations.SerializedName

class SearchList {

    data class Response(

        @SerializedName("Search")
        val search: List<Search.Response>
    )
}