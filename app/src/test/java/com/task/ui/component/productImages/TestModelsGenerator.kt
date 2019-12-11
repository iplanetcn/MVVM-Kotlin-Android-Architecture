package com.task.ui.component.productImages

import com.google.gson.Gson
import com.task.data.remote.dto.images.Image
import com.task.data.remote.dto.images.Images
import java.io.File

/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestModelsGenerator {
    lateinit var images: Images

    fun initImagesModel() {
        val gson = Gson()
        val jsonString = getJson("ProductInfo.json")
        images = gson.fromJson(jsonString, Images::class.java)
    }

    fun generateImagesModelWithEmptyList(): Images {
        val images = mutableListOf<Image>()
        this.images.images = images
        return this.images
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
