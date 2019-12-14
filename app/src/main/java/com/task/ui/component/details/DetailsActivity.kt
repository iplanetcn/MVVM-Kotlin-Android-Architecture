package com.task.ui.component.details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.task.R
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import kotlinx.android.synthetic.main.gif_image_item.*
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.details_layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getStringExtra(Constants.IMAGE_ITEM_KEY))
        viewModel.getGif()
        viewModel.uri.observe(this, Observer { bindGif(it) })
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    private fun bindGif(uri: String) {
        Glide.with(iv_product_image.context).asGif().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .load(uri).placeholder(R.drawable.car_icon).into(iv_product_image)
    }
}
