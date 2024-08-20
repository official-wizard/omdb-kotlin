package com.omdbapi.api.data.pojo

import com.google.gson.annotations.SerializedName

class Error {

    data class Response(

        @SerializedName("Response")
        val response: String,

        @SerializedName("Error")
        val error: String
    )
}