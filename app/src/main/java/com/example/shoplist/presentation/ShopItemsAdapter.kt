package com.example.shoplist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class ShopItemsAdapter() :
    RecyclerView.Adapter<ShopItemsAdapter.ViewHolder>() {

    var items: List<ShopItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()  // FIXME: rebuild entire RecyclerView. Used only for debugging
        }

    interface ClickListener {
        fun onClick(shopItem: ShopItem)
        fun onLongClick(shopItem: ShopItem) : Boolean
    }

    var clickListener: ClickListener? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(
        val view: View,
    ) : RecyclerView.ViewHolder(view) {
        val cardView = view.findViewById<CardView>(R.id.cardView_shopItem)
        val name = view.findViewById<TextView>(R.id.textViewName)
        val count = view.findViewById<TextView>(R.id.textViewCount)
    }

    val ITEM_ENADBED = 1
    val ITEM_DISNADBED = 2

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view =
            if (viewType == ITEM_DISNADBED){
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.shop_item_disabled, viewGroup, false)
            } else {  // ITEM_ENADBED
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.shop_item, viewGroup, false)
            }

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val type = if (items[position].isActive) ITEM_ENADBED else ITEM_DISNADBED
        return type
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val shopItem = items[position]
        viewHolder.name.text = shopItem.name
        viewHolder.count.text = shopItem.count.toString()
        viewHolder.cardView.setOnClickListener {
            clickListener?.onClick(shopItem)
        }

        viewHolder.cardView.setOnLongClickListener {
            clickListener?.onLongClick(shopItem) ?: false
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size

}