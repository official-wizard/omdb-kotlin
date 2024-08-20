package com.omdbapi.api

import co.infinum.retromock.Retromock
import com.haroldadmin.cnradapter.NetworkResponse
import com.omdbapi.api.core.Credentials
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import com.omdbapi.api.test.ResourceBodyFactory
import kotlin.test.assertNotNull

class OMDbInterfaceTest {

    @Test
    fun `check search list response parser`() {
        runBlocking {
            val api = createMockedApi()

            val searchList = api.searchList(
                title = "Demons"
            )

            assert(searchList is NetworkResponse.Success)
            assertNotNull((searchList as NetworkResponse.Success).body)
        }
    }

    @Test
    fun `check search response parser`() {
        runBlocking {
            val api = createMockedApi()

            val searchTitle = api.searchTitle(
                title = "Demons"
            )

            assert(searchTitle is NetworkResponse.Success)
            assertNotNull((searchTitle as NetworkResponse.Success).body)
        }
    }

    private fun createMockedApi(): OMDbInterface {

        val client = OMDbClient(Credentials("<web api key>"))
        val retro = client.omdbClient

        val mockRetrofit: Retromock = Retromock.Builder()
            .defaultBodyFactory(ResourceBodyFactory())
            .retrofit(retro)
            .build()

        return mockRetrofit.create(OMDbInterface::class.java)
    }
}