package com.task.ui.component.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.BaseViewModel
import com.task.usecase.NewsUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListViewModel @Inject
constructor(private val newsDataUseCase: NewsUseCase) : BaseViewModel() {

//    private val _noTaskIconRes = MutableLiveData<Int>()
//    val noTaskIconRes: LiveData<Int>
//        get() = _noTaskIconRes
    var newsLiveData: LiveData<Resource<NewsModel>> = newsDataUseCase.newsLiveData

    var newsSearchFound: MutableLiveData<NewsItem> = MutableLiveData()
    var noSearchFound: MutableLiveData<Unit> = MutableLiveData()

    fun getNews() {
        newsDataUseCase.getNews()
    }


    fun onSearchClick(newsTitle: String) {
        if (newsTitle.isNotEmpty()) {
            val newsItem = newsDataUseCase.searchByTitle(newsTitle)
            if (newsItem != null) {
                newsSearchFound.value = newsItem
            } else {
                noSearchFound.postValue(Unit)
            }
        } else {
            noSearchFound.postValue(Unit)
        }
    }
}
