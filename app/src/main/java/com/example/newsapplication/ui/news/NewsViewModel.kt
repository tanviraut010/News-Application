package com.example.newsapplication.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.data.Article
import com.example.newsapplication.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: RetroRepository) : ViewModel() {
    var liveDataList: MutableLiveData<ArrayList<Article>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<ArrayList<Article>> {
        return liveDataList
    }

    fun loadListOfData() {
        repository.safeApiCall("2352d41b50804fcebefbbe2fde7d8b13", "us", liveDataList)
    }
}