package com.task.ui.component.details

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.dto.images.Images
import com.task.ui.base.BaseViewModel
import com.task.utils.Constants
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsViewModel @Inject
constructor() : BaseViewModel() {
    private lateinit var images: Images
    private var index: Int = 0
    var uri: MutableLiveData<String> = MutableLiveData()

    fun initIntentData(images: Images, index: Int) {
        this.images = images
        this.index = index
    }

    fun getImage() {
        val imageUri = images.images?.get(index)?.uri
        uri.value = imageUri?.let { "${Constants.PREFFIX_URI_IMAGE}${imageUri}${Constants.SUFFIX_FULL_SIZE_IMAGE}" }
    }
}
