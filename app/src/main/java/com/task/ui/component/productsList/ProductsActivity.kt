package com.task.ui.component.productsList

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import com.task.R
import com.task.data.remote.dto.products.Product
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.productsList.productAdapter.ProductAdapter
import com.task.utils.Constants
import com.task.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ProductsActivity : BaseActivity(), RecyclerItemListener {
    @Inject
    lateinit var productsViewModel: ProductsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var productAdapter: ProductAdapter

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeViewModel() {
        productsViewModel = viewModelFactory.create(ProductsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            getProductList()
        }
        initializeProductsList()
        init(productsViewModel)
    }

    private fun init(productsViewModel: ProductsViewModel) {
        btn_all_filter.setOnClickListener {
            val availableProducts = productsViewModel.productsLiveData.value?.products
            if (availableProducts?.isNullOrEmpty() != true) {
                productAdapter.productList = availableProducts
            }
            productAdapter.notifyDataSetChanged()
        }

        btn_available_filter.setOnClickListener {
            val availableProducts = productsViewModel.productsLiveData.value?.products?.filter {
                it?.available ?: false
            }
            if (availableProducts?.isNullOrEmpty() != true) {
                productAdapter.productList = availableProducts
                productAdapter.notifyDataSetChanged()
            }
        }
        productsViewModel.noInterNetConnection.observe(this, Observer {
            if (it) {
                tv_no_data.visibility = VISIBLE
                cl_product_list.visibility = GONE
                toast(getString(R.string.no_internet))
                pb_loading.visibility = GONE
            }
        })

        productsViewModel.showError.observe(this, Observer {
            showDataView(false)
            toast("${it?.description}")
        })


        productsViewModel.productsLiveData.observe(this, Observer {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            if (!(it.products.isNullOrEmpty())) {
                productAdapter = ProductAdapter(this, it.products!!)
                rv_product_list.adapter = productAdapter
                showDataView(true)
            } else {
                showDataView(false)
                toast(getString(R.string.no_products_found))
            }
            tv_title.text = it.header?.headerTitle
            tv_description.text = it.header?.headerTitle
            EspressoIdlingResource.decrement()
        })
        getProductList()
    }

    private fun initializeProductsList() {
        val layoutManager = LinearLayoutManager(this)
        rv_product_list.layoutManager = layoutManager
        rv_product_list.setHasFixedSize(true)
    }

    override fun onItemSelected(position: Int) {
//        if (product) {
//            navigateToDetailsScreen(product)
//        } else {
//            toast(getString(R.string.cant_retrieve_image))
//        }
    }

    private fun getProductList() {
        pb_loading.visibility = VISIBLE
        tv_no_data.visibility = GONE
        cl_product_list.visibility = GONE
        EspressoIdlingResource.increment()
        productsViewModel.getGifs()
    }

    private fun navigateToDetailsScreen(product: Product) {
        startActivity(intentFor<DetailsActivity>(
                Constants.Product_ITEM_KEY to product
        ))
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        cl_product_list.visibility = if (show) VISIBLE else GONE
        pb_loading.visibility = GONE
    }
}
