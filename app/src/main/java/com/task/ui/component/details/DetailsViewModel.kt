package com.task.ui.component.details

import androidx.lifecycle.MutableLiveData
import com.task.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsViewModel @Inject
constructor() : BaseViewModel() {
    private lateinit var url: String
    var uri: MutableLiveData<String> = MutableLiveData()

    fun initIntentData(url: String) {
        this.url = url
    }

    fun getGif() {
        uri.value = url
    }
}
