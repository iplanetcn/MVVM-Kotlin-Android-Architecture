package com.task.ui.component.giphyGallery.giphyAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.task.R
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gif_image_item.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ImageViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Int, url: String, recyclerItemListener: RecyclerItemListener) {
        Glide.with(iv_gif_image.context).asGif().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .load(url).placeholder(R.drawable.ic_camera_roll_black_48dp).into(iv_gif_image)
        cl_image_item.setOnClickListener { recyclerItemListener.onItemSelected(position) }
    }
}

