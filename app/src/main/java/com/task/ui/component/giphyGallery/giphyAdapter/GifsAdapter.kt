package com.task.ui.component.giphyGallery.giphyAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.remote.dto.giphy.Gif
import com.task.ui.base.listeners.RecyclerItemListener

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class GifsAdapter(private val onItemClickListener: RecyclerItemListener, private val gifsList: List<Gif?>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(position, gifsList[position]?.images?.downsized?.url ?: "", onItemClickListener)
    }

    override fun getItemCount(): Int {
        return gifsList.size
    }
}

