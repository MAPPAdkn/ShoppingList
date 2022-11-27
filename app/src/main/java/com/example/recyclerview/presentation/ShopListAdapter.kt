package com.example.recyclerview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerview.R
import com.example.recyclerview.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClick: ((ShopItem) -> Unit)? = null
    var onShopItemLongClick: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent,false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = "${shopItem.name} is ${shopItem.enabled}"
        holder.tvCount.text = shopItem.count.toString()
        holder.itemView.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClick?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }

}