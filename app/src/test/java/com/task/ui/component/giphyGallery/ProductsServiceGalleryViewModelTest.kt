package com.task.ui.component.giphyGallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.remote.Error
import com.task.ui.base.listeners.BaseCallback
import com.task.ui.component.productsList.ProductsViewModel
import com.task.usecase.images.ProductUseCase
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ProductsServiceGalleryViewModelTest {
    // Subject under test
    private lateinit var productsViewModel: ProductsViewModel

    // Use a fake UseCase to be injected into the viewmodel
    private val productUseCase: ProductUseCase = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        // Create class under test
        // We initialise the repository with no tasks
        testModelsGenerator.initGifsDataModel()
        productsViewModel = ProductsViewModel(productUseCase)
    }

    @Test
    fun getGifsList() {
        // Let's do a synchronous answer for the callback
        val imagesModeltest = testModelsGenerator.gifsData
        //1- Mock - test
        (productsViewModel).apply {
            productsLiveData.value = imagesModeltest
        }
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { productUseCase.getGifs(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onSuccess(imagesModeltest) }
        //2-Call
        productsViewModel.getGifs()
        //3-verify
        assert(imagesModeltest == productsViewModel.productsLiveData.value)
    }

    @Test
    fun getEmptyImagesList() {
        // Let's do a synchronous answer for the callback
        val gifsModelstest = testModelsGenerator.generateGifsModelWithEmptyList()
        //1- Mock - test
        (productsViewModel).apply {
            productsLiveData.value = gifsModelstest
        }
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { productUseCase.getGifs(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onSuccess(gifsModelstest) }
        //2-Call
        productsViewModel.getGifs()
        //3-verify
        val gifsData = productsViewModel.productsLiveData.value
        assert(gifsData?.gifsList?.isNullOrEmpty() ?: false)
        assert(gifsModelstest == productsViewModel.productsLiveData.value)
    }

    @Test
    fun noInternetConnection() {
        // Let's do a synchronous answer for the callback
        val error = Error()
        //1- Mock - test
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { productUseCase.getGifs(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onFail(error) }
        //2-Call
        productsViewModel.getGifs()
        //3-verify
        assert(true == productsViewModel.noInterNetConnection.value)
    }

    @Test
    fun showError() {
        // Let's do a synchronous answer for the callback
        val errorString = "This is stup error message for testing"
        val error = Error(description = errorString, code = 1001)
        //1- Mock - test
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { productUseCase.getGifs(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onFail(error) }
        //2-Call
        productsViewModel.getGifs()
        //3-verify
        assert(error == productsViewModel.showError.value)
    }
}