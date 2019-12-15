package com.task

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.task.data.DataSource
import com.task.data.remote.Data
import com.task.data.remote.dto.giphy.Gif
import com.task.data.remote.dto.giphy.GifsData
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestDataReprository @Inject constructor() : DataSource {
    lateinit var gifsData: GifsData

    override fun requestGiphy(): Data? {
        val gson = Gson()
        val jsonString = getJson("GifsApiResponse.json")
        gifsData = gson.fromJson(jsonString, GifsData::class.java)
        return Data(code = 200, data = gifsData)
    }

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
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.assets.open(path)
        val inputAsString = inputStream.bufferedReader().use { it.readText() }
        return inputAsString
    }

//    @Throws(Exception::class)
//    fun convertStreamToString(inputStream: InputStream?): String {
//        val reader = BufferedReader(InputStreamReader(inputStream))
//        val sb = StringBuilder()
//        var line: String?
//        while (reader.readLine().also { line = it } != null) {
//            sb.append(line).append("\n")
//        }
//        reader.close()
//        return sb.toString()
//    }
//
//    @Throws(Exception::class)
//    fun getStringFromFile(filePath: String): String? {
//        val stream: InputStream = App.context.resources.assets.open(filePath)
//        val ret = convertStreamToString(stream)
//        //Make sure you close all streams.
//        stream.close()
//        return ret
//    }
}
