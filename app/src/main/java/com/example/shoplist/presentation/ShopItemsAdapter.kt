package com.example.shoplist.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem


class ShopItemsAdapter() :
//    RecyclerView.Adapter<ShopItemsAdapter.ViewHolder>() {
        ListAdapter<ShopItem, ShopItemsAdapter.ViewHolder>(ShopItemDiffUtil()){

    private val TAG = "XXXX"

//    var items: List<ShopItem> = listOf()
//        set(value) {
//            field = value
//            notifyDataSetChanged()  // FIXME: rebuild entire RecyclerView. Used only for debugging
//        }

    interface ItemsInteractionListener {
        fun onClick(shopItem: ShopItem)
        fun onLongClick(shopItem: ShopItem) : Boolean
        fun onSwiped(shopItem: ShopItem)
    }

    var itemsInteractionListener: ItemsInteractionListener? = null

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
//        val shopItem = items[position]
        val shopItem = getItem(position)
        val type = if (shopItem.isActive) ITEM_ENADBED else ITEM_DISNADBED
        return type
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

//        val shopItem = items[position]
        val shopItem = getItem(position)
        viewHolder.name.text = shopItem.name
        viewHolder.count.text = shopItem.count.toString()
        viewHolder.cardView.setOnClickListener {
            itemsInteractionListener?.onClick(shopItem)
        }

        viewHolder.cardView.setOnLongClickListener {
            itemsInteractionListener?.onLongClick(shopItem) ?: false
        }
    }

//    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = items.size  // this method is provided by ListAdapter

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                    or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            Log.d(TAG, "onSwiped: $position")
//            val shopItem = items[position]
            val shopItem = getItem(position)
            itemsInteractionListener?.onSwiped(shopItem)

//            notifyDataSetChanged()  // FIXME: rebuild entire RecyclerView. Used only for debugging

//            arrayList.remove(position)
//            adapter.notifyDataSetChanged()
        }
    }

}