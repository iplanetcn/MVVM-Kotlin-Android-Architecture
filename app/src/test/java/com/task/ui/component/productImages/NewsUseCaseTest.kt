package com.task.ui.component.productImages

import com.task.data.DataRepository
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.data.remote.dto.giphy.GifsData
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.GiphyUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created by ahmedeltaher on 3/8/17.
 */
@ExperimentalCoroutinesApi
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null
    private var callback: BaseCallback? = spyk()

    private lateinit var giphyUseCase: GiphyUseCase
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()
    private lateinit var gifsData: GifsData

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @BeforeEach
    fun setUp() {
        testModelsGenerator.initGifsDataModel()
        dataRepository = DataRepository(mockk(), mockk())
        giphyUseCase = GiphyUseCase(dataRepository!!, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetProductInfoSuccess() {
        gifsData = testModelsGenerator.gifsData
        val serviceResponse = Data(code = Error.SUCCESS_CODE, data = gifsData)
        coEvery { dataRepository?.requestGiphy() } returns serviceResponse
        giphyUseCase.getGifs(callback!!)
        coVerify(exactly = 1, verifyBlock = { callback?.onSuccess(any()) })
        coVerify(exactly = 0, verifyBlock = { callback?.onFail(any()) })
    }

    @Test
    fun testGetProductInfoFail() {
        val serviceResponse = Data(code = Error.ERROR_CODE, data = null)
        coEvery { dataRepository?.requestGiphy() } returns serviceResponse
        giphyUseCase.getGifs(callback!!)
        coVerify(exactly = 0, verifyBlock = { callback?.onSuccess(any()) })
        coVerify(exactly = 1, verifyBlock = { callback?.onFail(any()) })
    }

    @AfterEach
    fun tearDown() {
    }
}
