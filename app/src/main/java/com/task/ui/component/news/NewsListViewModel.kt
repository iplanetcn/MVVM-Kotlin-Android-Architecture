package com.task.ui.component.news

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.Error
import com.task.data.remote.dto.images.Images
import com.task.ui.base.BaseViewModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.ImagesUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListViewModel @Inject
constructor(imagesUseCase: ImagesUseCase) : BaseViewModel() {

    private var imagesUseCase: ImagesUseCase = imagesUseCase
    var imagedLiveData: MutableLiveData<Images> = MutableLiveData()
//    var newsSearchFound: MutableLiveData<NewsItem> = MutableLiveData()
//    var noSearchFound: MutableLiveData<Boolean> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<Error> = MutableLiveData()

    fun getImages() {
        imagesUseCase.getImages(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: Any?) {
            imagedLiveData.postValue(data as Images)
        }

        override fun onFail(error: Error) {
            if (error.code == Error.NO_INTERNET_CONNECTION) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()

    }
}
