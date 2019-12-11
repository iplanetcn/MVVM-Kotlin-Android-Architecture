package com.task.ui.component.productImages.newsAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.images.Image
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.utils.Constants
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.product_image_item.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ImageViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Int, image: Image, recyclerItemListener: RecyclerItemListener) {
        image.uri?.let {
            val url = "${Constants.PREFFIX_URI_IMAGE}$it${Constants.SUFFIX_THUMBNAIL_IMAGE}"
            Picasso.get().load(url).placeholder(R.drawable.car_icon).error(R.drawable.car_icon).fit().centerCrop().into(iv_product_image)
        }
        cl_image_item.setOnClickListener { recyclerItemListener.onItemSelected(position) }
    }
}

