package com.task.ui.component.giphyGallery

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.Error
import com.task.data.remote.dto.giphy.Gif
import com.task.data.remote.dto.giphy.GifsData
import com.task.ui.base.BaseViewModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.GiphyUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class GiphyGalleryViewModel @Inject
constructor(giphyUseCase: GiphyUseCase) : BaseViewModel() {

    private var giphyUseCase = giphyUseCase
    var gifsLiveData: MutableLiveData<GifsData> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<Error> = MutableLiveData()

    fun getGifs() {
        giphyUseCase.getGifs(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: Any?) {
            gifsLiveData.postValue(data as GifsData)
        }

        override fun onFail(error: Error) {
            if (error.code == Error.NO_INTERNET_CONNECTION) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }

        }
    }

    fun getGif(index: Int): Gif? {
        gifsLiveData.value?.gifsList?.get(index)?.let { return it }
        return null
    }
}
