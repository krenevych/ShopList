package com.example.shoplist.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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

        setupLiveData()

        setupButtonSave()

        initViews()
    }

    private fun setupButtonSave() {
        binding.buttonSave.setOnClickListener {
            val nameEditable = binding.editTextName.text
            val countEditable = binding.editTextCount.text

            if (mode == MODE_EDIT) {
                viewModel.editItem(nameEditable, countEditable)
            } else { // mode == MODE_ADD
                viewModel.addItem(nameEditable, countEditable)
            }
        }
    }


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
                        viewModel.getItem(itemId)
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

    private fun setupLiveData() {
        viewModel.itemLiveData.observe(this) {
            with(binding) {
                editTextName.setText(it.name)
                editTextCount.setText(it.count.toString())
            }
        }

        viewModel.finishEditLiveData.observe(this) {
            onBackPressed()
        }

        viewModel.nameErrorLiveData.observe(this) {
            if (it) {
                binding.tilName.error = "Name error"
            }
        }

        viewModel.countErrorLiveData.observe(this) {
            if (it) {
                binding.tilCount.error = "Count error"
            }
        }
    }

    private fun initViews() {
//        addTextChangedListener - observes if text field is changed
        with(binding){
            editTextName.addTextChangedListener {
                tilName.error = null
            }

            editTextCount.addTextChangedListener {
                tilCount.error = null
            }
        }




    }

}