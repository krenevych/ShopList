package com.example.shoplist.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val shopItemsAdapter = ShopItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.itemsLiveData.observe(this) {
            // Calls each time, when data is changed
            Log.d(TAG, "getItemUseCase: $it")

//            shopItemsAdapter.items = it
            shopItemsAdapter.submitList(it)
        }


        binding.addBtn.setOnClickListener {
//            viewModel.addItem(ShopItem("Bread", 4))

            startShopItemActivityForAdd()

        }

        binding.shopItems.layoutManager = LinearLayoutManager(this)
        binding.shopItems.adapter = shopItemsAdapter

        shopItemsAdapter.itemsInteractionListener = object : ShopItemsAdapter.ItemsInteractionListener {
            override fun onClick(shopItem: ShopItem) {
                Log.d(TAG, "onClick: $shopItem")

                startShopItemActivityForEdit(shopItem)
            }

            override fun onLongClick(shopItem: ShopItem): Boolean {
                Log.d(TAG, "onLongClick: $shopItem")
                viewModel.toggleItem(shopItem)
                return true
            }

            override fun onSwiped(shopItem: ShopItem) {
                viewModel.removeItem(shopItem)
            }
        }

        val itemTouchHelper = ItemTouchHelper(shopItemsAdapter.simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.shopItems)
    }

    private fun startShopItemActivityForEdit(shopItem: ShopItem) {
        val intent = Intent(this, ShopItemActivity::class.java).apply {
            putExtra(EXTRA_MODE, MODE_EDIT)
            putExtra(EXTRA_ITEM_ID, shopItem.id)
        }
        startActivity(intent)
    }

    private fun startShopItemActivityForAdd() {
        val intent = Intent(this, ShopItemActivity::class.java).apply {
            putExtra(EXTRA_MODE, MODE_ADD)
        }
        startActivity(intent)
    }
}