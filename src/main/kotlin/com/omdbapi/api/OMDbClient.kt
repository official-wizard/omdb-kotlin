package com.omdbapi.api

import com.omdbapi.api.core.Credentials
import com.omdbapi.api.core.CoreClient

class OMDbClient(credentials: Credentials, debugging: Boolean = false)
    : CoreClient(credentials, "https://www.omdbapi.com/", debugging) {

    // the API interface for the client
    val api: OMDbInterface = omdbClient.create(OMDbInterface::class.java)
}
