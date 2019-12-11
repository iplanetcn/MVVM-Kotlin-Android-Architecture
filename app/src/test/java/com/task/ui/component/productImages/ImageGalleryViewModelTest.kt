package com.task.ui.component.productImages

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.remote.Error
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.ImagesUseCase
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
class ImageGalleryViewModelTest {
    // Subject under test
    private lateinit var imageGalleryViewModel: ImageGalleryViewModel

    // Use a fake UseCase to be injected into the viewmodel
    private val imagesUseCase: ImagesUseCase = mockk()

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
        testModelsGenerator.initImagesModel()
        imageGalleryViewModel = ImageGalleryViewModel(imagesUseCase)
    }

    @Test
    fun getImagesList() {
        // Let's do a synchronous answer for the callback
        val imagesModeltest = testModelsGenerator.images
        //1- Mock - test
        (imageGalleryViewModel).apply {
            imagesLiveData.value = imagesModeltest
        }
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { imagesUseCase.getImages(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onSuccess(imagesModeltest) }
        //2-Call
        imageGalleryViewModel.getImages()
        //3-verify
        assert(imagesModeltest == imageGalleryViewModel.imagesLiveData.value)
    }

    @Test
    fun getEmptyImagesList() {
        // Let's do a synchronous answer for the callback
        val imagesModeltest = testModelsGenerator.generateImagesModelWithEmptyList()
        //1- Mock - test
        (imageGalleryViewModel).apply {
            imagesLiveData.value = imagesModeltest
        }
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { imagesUseCase.getImages(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onSuccess(imagesModeltest) }
        //2-Call
        imageGalleryViewModel.getImages()
        //3-verify
        val images = imageGalleryViewModel.imagesLiveData.value
        assert(images?.images?.isNullOrEmpty() ?: false)
        assert(imagesModeltest == imageGalleryViewModel.imagesLiveData.value)
    }

    @Test
    fun noInternetConnection() {
        // Let's do a synchronous answer for the callback
        val error = Error()
        //1- Mock - test
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { imagesUseCase.getImages(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onFail(error) }
        //2-Call
        imageGalleryViewModel.getImages()
        //3-verify
        assert(true == imageGalleryViewModel.noInterNetConnection.value)
    }

    @Test
    fun showError() {
        // Let's do a synchronous answer for the callback
        val errorString = "This is stup error message for testing"
        val error = Error(description = errorString, code = 1001)
        //1- Mock - test
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { imagesUseCase.getImages(callback = capture(callbackCapture)) } answers
                { callbackCapture.captured.onFail(error) }
        //2-Call
        imageGalleryViewModel.getImages()
        //3-verify
        assert(error == imageGalleryViewModel.showError.value)
    }
}