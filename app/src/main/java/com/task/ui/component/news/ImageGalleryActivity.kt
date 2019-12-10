package com.task.ui.component.news

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingResource
import com.task.R
import com.task.data.remote.dto.images.Images
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.newsAdapter.ImagesAdapter
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

class ImageGalleryActivity : BaseActivity(), RecyclerItemListener {
    @Inject
    lateinit var imageGalleryViewModel: ImageGalleryViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeViewModel() {
        imageGalleryViewModel = viewModelFactory.create(ImageGalleryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            getImages()
        }
        initializeNewsList()
        init(imageGalleryViewModel)
    }

    private fun init(viewModel: ImageGalleryViewModel) {
        viewModel.noInterNetConnection.observe(this, Observer {
            if (it) {
                tv_no_data.visibility = VISIBLE
                cl_images_list.visibility = GONE
                toast(getString(R.string.no_internet))
                pb_loading.visibility = GONE
            }
        })

        viewModel.showError.observe(this, Observer {
            showDataView(false)
            toast("${it?.description}")
        })


        viewModel.imagesLiveData.observe(this, Observer {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            if (!(it.images.isNullOrEmpty())) {
                val newsAdapter = ImagesAdapter(this, it.images!!)
                rv_images_list.adapter = newsAdapter
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
        val layoutManager = GridLayoutManager(this, 3)
        rv_images_list.layoutManager = layoutManager
    }

    override fun onItemSelected(position: Int) =
            this.navigateToDetailsScreen(images = imageGalleryViewModel.imagesLiveData.value!!, index = position)

    private fun getImages() {
        pb_loading.visibility = VISIBLE
        tv_no_data.visibility = GONE
        cl_images_list.visibility = GONE
        EspressoIdlingResource.increment()
        imageGalleryViewModel.getImages()
    }

    private fun navigateToDetailsScreen(images: Images, index: Int) {
        startActivity(intentFor<DetailsActivity>(
                Constants.IMAGE_ITEM_KEY to images,
                Constants.IMAGE_INDEX_KEY to index
        ))
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        cl_images_list.visibility = if (show) VISIBLE else GONE
        pb_loading.visibility = GONE
    }
}
