package com.example.learn.business

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.learn.R
import com.example.learn.databinding.MerchantPageLayoutBinding

class MerchantPageLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    lateinit var pagerAdapter: MerchantPageAdapter
    var binding:MerchantPageLayoutBinding

    init {
        binding  = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.merchant_page_layout,this,true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        pagerAdapter = MerchantPageAdapter(context)
        binding.vPager.adapter = pagerAdapter
        binding.vSmartTab.setViewPager(binding.vPager)
    }

    fun canScrollVertically(): Boolean {
        val view = (pagerAdapter.getItem(binding.vPager.currentItem) as ScrollableViewProvider).getScrollableView()
        return view.canScrollVertically(-1)
    }
}

class MerchantPageAdapter(context: Context) : PagerAdapter() {
    private val layFood = MerchantFoodLayout(context)
    private val layInfo = MerchantInfoLayout(context)
    private val layComment = MerchantCommentLayout(context)

    override fun getCount(): Int = 3
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)
    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "点菜"
        1 -> "评论(9999+)"
        2 -> "商家"
        else -> ""
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val content = getItem(position)
        container.addView(content)
        return content
    }

    fun getItem(position: Int): View {
        return when (position) {
            0 -> layFood
            1 -> layComment
            2 -> layInfo
            else -> throw RuntimeException("getItem error position $position")
        }
    }
}

interface ScrollableViewProvider {
    fun getScrollableView(): View
}