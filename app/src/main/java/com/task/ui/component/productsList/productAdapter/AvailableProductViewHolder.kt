package com.task.ui.component.productsList.productAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.products.Product
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.product_available_layout.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class AvailableProductViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Int, product: Product?, recyclerItemListener: RecyclerItemListener) {
        tv_title.text = product?.description ?: ""
        tv_description.text = product?.longDescription ?: ""
        Picasso.get().load(product?.imageURL).placeholder(R.drawable.ic_camera_roll_black_48dp).error(R.drawable.ic_camera_roll_black_48dp).into(iv_product)
        cl_product_item.setOnClickListener { recyclerItemListener.onItemSelected(position) }
        rb_product_rating.rating = product?.rating?.toFloat() ?: 0f
    }
}

