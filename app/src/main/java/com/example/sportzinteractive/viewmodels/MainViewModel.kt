package com.example.sportzinteractive.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.sportzinteractive.R
import com.example.sportzinteractive.app.MyApplication
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.util.Resource
import com.hadi.retrofitmvvm.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {


    val matchDetailLiveData: MutableLiveData<Resource<MatchDetailsResponse>> = MutableLiveData()
    lateinit var matchDetailsResponse : MatchDetailsResponse
    init {
        getMatchDetailData()
    }

    fun getMatchDetailData() = viewModelScope.launch {
        fetchApiData()
    }

    private suspend fun fetchApiData() {
        matchDetailLiveData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getMatchDetails()
                matchDetailsResponse = response.body()!!
                matchDetailLiveData.postValue(handleResponse(response))
            } else {
                matchDetailLiveData.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
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

    private fun handleResponse(response: Response<MatchDetailsResponse>): Resource<MatchDetailsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}