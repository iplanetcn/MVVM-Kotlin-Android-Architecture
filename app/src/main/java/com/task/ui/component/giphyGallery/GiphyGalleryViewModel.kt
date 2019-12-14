package com.task.ui.component.giphyGallery

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.Error
import com.task.data.remote.dto.giphy.Gify
import com.task.ui.base.BaseViewModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.GiphyUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class GiphyGalleryViewModel @Inject
constructor(giphyUseCase: GiphyUseCase) : BaseViewModel() {

    private var imagesUseCase = giphyUseCase
    var imagesLiveData: MutableLiveData<Gify> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<Error> = MutableLiveData()

    fun getImages() {
        imagesUseCase.getImages(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: Any?) {
            imagesLiveData.postValue(data as Gify)
        }

        override fun onFail(error: Error) {
            if (error.code == Error.NO_INTERNET_CONNECTION) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }

        }
    }
}
