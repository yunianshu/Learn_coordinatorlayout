package com.example.learn.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.learn.R

class MerchantCommentLayout(context: Context) : FrameLayout(context), ScrollableViewProvider {
    override fun getScrollableView(): View {
        return findViewById(R.id.vRecycler)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.merchant_page_cell_layout, this)
        initialData()
    }

    private fun initialData() {
        findViewById<RecyclerView>(R.id.vRecycler).setBackgroundColor(0xFFEFEFEF.toInt())
    }
}