package com.task.ui.component.giphyGallery

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingResource
import com.facebook.drawee.backends.pipeline.Fresco
import com.task.R
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.giphyGallery.giphyAdapter.ImagesAdapter
import com.task.utils.Constants
import com.task.utils.EspressoIdlingResource
import com.task.utils.MiddleDividerItemDecoration
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class GiphyGalleryActivity : BaseActivity(), RecyclerItemListener {
    @Inject
    lateinit var giphyGalleryViewModel: GiphyGalleryViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeViewModel() {
        giphyGalleryViewModel = viewModelFactory.create(GiphyGalleryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            getImages()
        }
        initializeNewsList()
        init(giphyGalleryViewModel)
    }

    private fun init(galleryViewModel: GiphyGalleryViewModel) {
        galleryViewModel.noInterNetConnection.observe(this, Observer {
            if (it) {
                tv_no_data.visibility = VISIBLE
                cl_images_list.visibility = GONE
                toast(getString(R.string.no_internet))
                pb_loading.visibility = GONE
            }
        })

        galleryViewModel.showError.observe(this, Observer {
            showDataView(false)
            toast("${it?.description}")
        })


        galleryViewModel.imagesLiveData.observe(this, Observer {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            if (!(it.info.isNullOrEmpty())) {
                val imagesAdapter = ImagesAdapter(this, it.info!!)
                rv_images_list.adapter = imagesAdapter
                showDataView(true)
            } else {
                showDataView(false)
                toast(getString(R.string.wrong_message))
            }
            EspressoIdlingResource.decrement()
        })
        getImages()
    }

    private fun initializeNewsList() {
        val itemDecoration = MiddleDividerItemDecoration(this, MiddleDividerItemDecoration.ALL)
        itemDecoration.setDividerColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        rv_images_list.addItemDecoration(itemDecoration)
        rv_images_list.layoutManager = GridLayoutManager(this, 3)
    }

    override fun onItemSelected(position: Int) {
        val image = giphyGalleryViewModel.imagesLiveData.value?.info?.get(position)
        if (image != null && !image.images?.downsized?.url.isNullOrEmpty()) {
            navigateToDetailsScreen(image?.images?.fixedHeight?.url!!)
        } else {
            toast(getString(R.string.cant_retrieve_image))
        }
    }

    private fun getImages() {
        pb_loading.visibility = VISIBLE
        tv_no_data.visibility = GONE
        cl_images_list.visibility = GONE
        EspressoIdlingResource.increment()
        giphyGalleryViewModel.getImages()
    }

    private fun navigateToDetailsScreen(uri: String) {
        startActivity(intentFor<DetailsActivity>(
                Constants.IMAGE_ITEM_KEY to uri
        ))
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        cl_images_list.visibility = if (show) VISIBLE else GONE
        pb_loading.visibility = GONE
    }
}
