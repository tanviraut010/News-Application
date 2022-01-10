package com.example.newsapplication.network

import androidx.lifecycle.MutableLiveData
import com.example.newsapplication.data.Article
import com.example.newsapplication.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val apiInstance: ApiServices) {

    fun safeApiCall(
        apiKey: String,
        country: String,
        liveDataList: MutableLiveData<ArrayList<Article>>
    ) {
        val call: Call<NewsResponse> = apiInstance.fetchNewsList(apiKey, country)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                liveDataList.postValue(response.body()?.articles!!)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }
}