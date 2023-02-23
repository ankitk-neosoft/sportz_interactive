package com.example.sportzinteractive.network

import com.example.sportzinteractive.model.MatchDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("{json}")
    suspend fun getMatchDetails(@Path("json") json : String): Response<MatchDetailsResponse>
}