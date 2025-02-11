package com.ferhatozcelik.codechallenge.data.remote

import com.ferhatozcelik.codechallenge.data.model.ExampleModel
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @GET("/api/v1/example")
    suspend fun getExampleResult(): Response<List<ExampleModel>>

}