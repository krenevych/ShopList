package com.example.shoplist.presentation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.databinding.ActivityEditShopItemBinding
import com.example.shoplist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private val TAG: String = "XXXX"
    private val binding by lazy {
        ActivityEditShopItemBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<ShopItemViewModel> {
        ShopItemViewModelFactory()
    }

    class ShopItemViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShopItemViewModel(RepositoryImpl) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        parseItemIntent(intent)
        Log.d(TAG, "parse intent: mode=$mode, id=$itemId")

        setupButtonSave()

    }


    private fun setupButtonSave() {


        binding.buttonSave.setOnClickListener {
            val name = parseName(binding.editTextName.text)
            val count = parseCount(binding.editTextCount.text)

            if (mode == MODE_EDIT) {
//                viewModel.editItem(ShopItem())
            } else { // mode == MODE_ADD
                viewModel.addItem(name, count)
            }
        }
    }

    private fun parseName(nameEditable: Editable?): String {
        return nameEditable.toString()
    }

    private fun parseCount(countEditable: Editable?): Int {
        return countEditable.toString().toInt()
    }

//    private fun parseEditData(text: Editable?, text1: Editable?) {
//        TODO("Not yet implemented")
//    }

    private var mode: String = MODE_UNDEFINED
    private var itemId = ShopItem.UNDEFINED_ID
    private fun parseItemIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEFINED
            when (mode) {
                MODE_EDIT -> {
                    if (intent.hasExtra(EXTRA_ITEM_ID)) {
                        itemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
                        if (itemId == ShopItem.UNDEFINED_ID) {
                            throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                        }
                    } else {
                        throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                    }
                }

                MODE_ADD -> {

                }

                MODE_UNDEFINED -> throw IllegalArgumentException("Mode isn't defined")
            }
        } else {
            throw IllegalArgumentException("Mode isn't defined")
        }
    }


}