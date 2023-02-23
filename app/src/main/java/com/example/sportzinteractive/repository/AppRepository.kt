package com.example.sportzinteractive.repository

import com.example.sportzinteractive.network.RetrofitInstance

class AppRepository {
    suspend fun getMatchDetails() = RetrofitInstance.retrofitClient.getMatchDetails()
}