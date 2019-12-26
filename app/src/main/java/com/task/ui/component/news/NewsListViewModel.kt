package com.task.ui.component.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.remote.Error
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.BaseViewModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListViewModel @Inject
constructor(private val newsDataUseCase: NewsUseCase) : BaseViewModel() {
    var newsLiveData: LiveData<Resource<NewsModel>> = newsDataUseCase.newsLiveData
    var newsSearchFound: MutableLiveData<NewsItem> = MutableLiveData()
    var noSearchFound: MutableLiveData<Boolean> = MutableLiveData()

    fun getNews() {
        newsDataUseCase.getNews(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: Any) {
            println("{live data is here ${newsLiveData.value?.data}")
        }

        override fun onFail(error: Error) {

        }

    }

    fun onSearchClick(newsTitle: String) {
        if (newsTitle.isNotEmpty()) {
            newsSearchFound.value = newsDataUseCase.searchByTitle(newsTitle)
            noSearchFound.value = (newsSearchFound.value == null)
        } else {
            noSearchFound.value = true
        }
    }
}
