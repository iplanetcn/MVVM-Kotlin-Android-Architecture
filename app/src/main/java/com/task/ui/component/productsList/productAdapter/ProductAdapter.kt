package com.task.ui.component.productsList.productAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.remote.dto.products.Product
import com.task.ui.base.listeners.RecyclerItemListener


/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ProductAdapter(private val onItemClickListener: RecyclerItemListener,var productList: List<Product?>) : RecyclerView.Adapter<AvalibleProductViewHolder>() {
    private val TYPE_AVAILABLE = 1
    private val TYPE_NOT_AVAILABLE = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvalibleProductViewHolder {
        if (viewType == TYPE_NOT_AVAILABLE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.product_inavailable_layout, parent, false)
            return AvalibleProductViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_available_layout, parent, false)
        return AvalibleProductViewHolder(view)
    }

    override fun onBindViewHolder(holderAvalible: AvalibleProductViewHolder, position: Int) {
        holderAvalible.bind(position, productList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (productList[position]?.available == true) {
            return TYPE_AVAILABLE
        } else {
            return TYPE_NOT_AVAILABLE
        }

    }
}

