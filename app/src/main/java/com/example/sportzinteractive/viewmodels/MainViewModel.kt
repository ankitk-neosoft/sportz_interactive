package com.example.sportzinteractive.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportzinteractive.R
import com.example.sportzinteractive.app.MyApplication
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.util.Resource
import com.hadi.retrofitmvvm.util.Utils.hasInternetConnection
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {


    val matchDetailLiveData: MutableLiveData<Resource<List<MatchDetailsResponse>>> =
        MutableLiveData()

    private var list: MutableList<Response<MatchDetailsResponse>> =
        listOf<Response<MatchDetailsResponse>>().toMutableList()

    init {
        getMatchDetailData()
    }

    //coroutine to fetch api within scope of view model
    fun getMatchDetailData() = viewModelScope.launch {

        matchDetailLiveData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response1 = async { appRepository.getMatchDetails("sapk01222019186652.json") }
                val response2 = async { appRepository.getMatchDetails("nzin01312019187360.json") }
                list.clear()
                list.add(response1.await())
                list.add(response2.await())
                matchDetailLiveData.postValue(handleResponse(list))
            } else {
                matchDetailLiveData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.no_internet_connection
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> matchDetailLiveData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.network_failure
                        )
                    )
                )
                else -> matchDetailLiveData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.conversion_error
                        )
                    )
                )
            }
        }
    }


    private var finalList: MutableList<MatchDetailsResponse> =
        listOf<MatchDetailsResponse>().toMutableList()

    private fun handleResponse(response: List<Response<MatchDetailsResponse>>): Resource<List<MatchDetailsResponse>> {

        finalList.clear()
        response.forEach {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    finalList.add(resultResponse)
                }
            }
        }
        return if (finalList.isNotEmpty()) {
            Resource.Success(finalList)
        } else Resource.Error("Something wrong")

    }
}