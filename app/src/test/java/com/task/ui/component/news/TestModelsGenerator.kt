package com.task.ui.component.news

import com.google.gson.Gson
import com.task.data.remote.dto.images.Image
import com.task.data.remote.dto.images.Images
import io.mockk.spyk
import java.io.File

/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestModelsGenerator {
    lateinit var images: Images

    fun initImagesModel() {
        val gson: Gson = Gson()
        var jsonString = getJson("ProductInfo.json")
        images = gson.fromJson(jsonString, Images::class.java)
    }

    fun generateImagesModelWithEmptyList(): Images {
        var imagesModel: Images = spyk()
        val images = mutableListOf<Image>()
        imagesModel.images = images
        return imagesModel
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
