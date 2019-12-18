package com.task.ui.component.productsList

import androidx.lifecycle.MutableLiveData
import com.task.data.remote.Error
import com.task.data.remote.dto.products.Product
import com.task.data.remote.dto.products.Products
import com.task.ui.base.BaseViewModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.images.ProductUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ProductsViewModel @Inject
constructor(productUseCase: ProductUseCase) : BaseViewModel() {

    private var productUseCase = productUseCase
    var productsLiveData: MutableLiveData<Products> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<Error> = MutableLiveData()

    fun getGifs() {
        productUseCase.getGifs(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: Any?) {
            productsLiveData.postValue(data as Products)
        }

        override fun onFail(error: Error) {
            if (error.code == Error.NO_INTERNET_CONNECTION) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }

        }
    }

    fun getProduct(index: Int): Product? {
        return null
    }
}
