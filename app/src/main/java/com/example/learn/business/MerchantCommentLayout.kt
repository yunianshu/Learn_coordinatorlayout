package com.example.learn.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learn.R
import com.example.learn.databinding.MerchantPageCellLayoutBinding

class MerchantCommentLayout(context: Context) : FrameLayout(context), ScrollableViewProvider {

    var binding: MerchantPageCellLayoutBinding
    override fun getScrollableView(): View {
        return binding.vRecycler
    }

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.merchant_page_cell_layout,this,true)
        initialData()
    }

    private fun initialData() {
        binding.vRecycler.setBackgroundColor(0xFFEFEFEF.toInt())
    }
}