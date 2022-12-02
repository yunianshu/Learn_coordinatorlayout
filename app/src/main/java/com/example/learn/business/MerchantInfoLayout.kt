package com.example.learn.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.learn.R
import com.example.learn.databinding.MerchantPageCellLayoutBinding
import com.example.learn.databinding.MerchantPageLayoutBinding

class MerchantInfoLayout(context: Context) : FrameLayout(context), ScrollableViewProvider {

    var binding:MerchantPageCellLayoutBinding
    override fun getScrollableView(): View {
        return binding.vRecycler
    }

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.merchant_page_cell_layout,this,true)
        initialData()
    }

    private fun initialData() {
    }
}