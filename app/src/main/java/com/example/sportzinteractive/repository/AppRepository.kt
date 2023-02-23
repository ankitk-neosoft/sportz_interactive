package com.example.sportzinteractive.repository

import com.example.sportzinteractive.network.RetrofitInstance

class AppRepository {
    //repo using suspend function of coroutine
    suspend fun getMatchDetails(json : String) = RetrofitInstance.retrofitClient.getMatchDetails(json)
}