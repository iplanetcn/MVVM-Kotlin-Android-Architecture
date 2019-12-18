package com.task.ui.component.giphyGallery

import com.google.gson.Gson
import java.io.File

/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestModelsGenerator {
    lateinit var gifsData: GifsData

    fun initGifsDataModel() {
        val gson = Gson()
        val jsonString = getJson("GifsApiResponse.json")
        gifsData = gson.fromJson(jsonString, GifsData::class.java)
    }

    fun generateGifsModelWithEmptyList(): GifsData {
        this.gifsData.gifsList = mutableListOf<Gif>()
        return this.gifsData
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
