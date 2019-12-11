package com.task.ui.component.productImages

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
}