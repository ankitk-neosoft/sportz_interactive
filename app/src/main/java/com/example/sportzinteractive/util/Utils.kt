package com.hadi.retrofitmvvm.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.sportzinteractive.app.MyApplication
import com.example.sportzinteractive.model.PlayerDetails

object Utils {
    fun hasInternetConnection(application: MyApplication): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return false
    }

    fun playerTag(playerDetails: PlayerDetails) :CharSequence {

        return if(playerDetails.iscaptain == true && playerDetails.iskeeper == true)
            "(c & wk)"
        else if (playerDetails.iscaptain == true )
            "(c)"
        else if (playerDetails.iskeeper == true )
            "(wk)"
        else ""

    }
}