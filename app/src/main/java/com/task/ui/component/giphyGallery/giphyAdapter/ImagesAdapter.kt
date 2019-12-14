package com.task.ui.component.giphyGallery.giphyAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.remote.dto.giphy.Data
import com.task.ui.base.listeners.RecyclerItemListener

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ImagesAdapter(private val onItemClickListener: RecyclerItemListener, private val giphies: List<Data?>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(position, giphies[position]?.images?.downsized?.url ?: "", onItemClickListener)
    }

    override fun getItemCount(): Int {
        return giphies.size
    }
}

