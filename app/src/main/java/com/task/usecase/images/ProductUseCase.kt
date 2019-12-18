package com.task.usecase.images

import com.task.data.DataSource
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.ui.base.listeners.BaseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProductUseCase @Inject
constructor(private val dataRepository: DataSource, override val coroutineContext: CoroutineContext) : CoroutineScope {

    fun getGifs(callback: BaseCallback) {
        launch {
            try {
                val serviceResponse: Data? = withContext(Dispatchers.IO) { dataRepository.requestProducts() }
                if (serviceResponse?.code == Error.SUCCESS_CODE) {
                    val data = serviceResponse.data
                    callback.onSuccess(data)
                } else {
                    callback.onFail(serviceResponse?.error
                            ?: Error(code = Error.INTERNAL_SERVER_ERROR))
                }
            } catch (e: Exception) {
                callback.onFail(Error(e))
            }
        }
    }
}