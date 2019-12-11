package com.task.ui.component.details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.task.R
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import kotlinx.android.synthetic.main.details_layout.*
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
        viewModel.initIntentData(intent.getParcelableExtra(Constants.IMAGE_ITEM_KEY))
        viewModel.getImage()
        viewModel.uri.observe(this, Observer { bindImage(it) })
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    private fun bindImage(uri: String) {
            Picasso.get().load(uri).placeholder(R.drawable.cars_icon).error(R.drawable.cars_icon).into(iv_product_full_Image)
    }
}
