package com.task.ui.component.details

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.dto.images.Image
import com.task.ui.base.BaseViewModel
import com.task.utils.Constants
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsViewModel @Inject
constructor() : BaseViewModel() {
    private lateinit var image: Image
    var uri: MutableLiveData<String> = MutableLiveData()

    fun initIntentData(image: Image) {
        this.image = image
    }

    fun getImage() {
        val imageUri = image.uri
        uri.value = imageUri?.let { "${Constants.PREFFIX_URI_IMAGE}${imageUri}${Constants.SUFFIX_FULL_SIZE_IMAGE}" }
    }
}
