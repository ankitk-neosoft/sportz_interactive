package com.example.sportzinteractive.network

import com.example.sportzinteractive.model.MatchDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET("sapk01222019186652.json")
    suspend fun getMatchDetails(): Response<MatchDetailsResponse>

}