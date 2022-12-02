package com.example.learn.business

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.learn.R
import com.example.learn.databinding.MerchantContentLayoutBinding
import com.example.learn.dp
import com.example.learn.load
import com.example.learn.view.stateRefresh
import com.example.learn.view.stateSave
import com.example.learn.view.statesChangeByAnimation
import jp.wasabeef.glide.transformations.BlurTransformation


@SuppressLint("ClickableViewAccessibility")
class MerchantContentLayout(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    var laySettle: MerchantSettleLayout? = null
    private var firstLayout: Boolean = false
    private var isExpanded = false
    private var effected: Float = 0f
    lateinit var binding: MerchantContentLayoutBinding

    init {
        binding  = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.merchant_content_layout,this,true)
        binding.apply {
            vCover.load(R.mipmap.cover, listOf(BlurTransformation()))

            layScroll.setOnTouchListener { _, _ -> !isExpanded }

            vTicket1.set(3, 27, "2018.06.12")
            vTicket2.set(5, 40, "2018.06.12")

            vSwitch.setOnClickListener { switch(!isExpanded) }
            vHide.setOnClickListener { switch(!isExpanded) }
        }
    }

    private fun animViews(): Array<View> =
        arrayOf(binding.laySimple, binding.vAvatar, binding.vMerchantName,binding.layTicket, binding.vTicket1, binding.vTicket2, binding.vSwitchIcon)

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (!firstLayout) {
            firstLayout = true
            binding.apply {
                laySimple.stateSave(R.id.vs1).a(1F)
                laySimple.stateSave(R.id.vs2).a(0F)
                vMerchantName.stateSave(R.id.vs1).a(0F)
                vMerchantName.stateSave(R.id.vs2).a(1F)
                layTicket.stateSave(R.id.vs1).mt(dp(15))
                layTicket.stateSave(R.id.vs2).mt(dp(70))

                vSwitchIcon.stateSave(R.id.vs1)
                vSwitchIcon.stateSave(R.id.vs2).r(180F)

                vAvatar.stateSave(R.id.vs1)
                val tx: Float =
                    ((width - vAvatar.width) / 2 - (vAvatar.layoutParams as MarginLayoutParams).leftMargin).toFloat()
                vAvatar.stateSave(R.id.vs2).tx(tx).ty(dp(10).toFloat())
            }
        }
    }

    // 效果简单实现，具体内容应该根据业务动态计算变化高度区间。
    fun effectByOffset(transY: Float) {
        val p2: Float = when {
            transY <= dp(10) -> 0F
            transY > dp(10) && transY < dp(30) -> (transY - dp(10)) / dp(20)
            else -> 1F
        }
        binding.vt2.alpha = p2
        binding.vt222.alpha = p2

        val p3: Float = when {
            transY <= dp(40) -> 0F
            transY > dp(40) && transY < dp(60) -> (transY - dp(40)) / dp(20)
            else -> 1F
        }
       binding.vt3.alpha = p3
        binding.vt333.alpha = p3

        val p4: Float = when {
            transY <= dp(70) -> 0F
            transY > dp(70) && transY < dp(90) -> (transY - dp(70)) / dp(20)
            else -> 1F
        }
       binding.vt4.alpha = p4
        binding.vt444.alpha = p4

        val p5: Float = when {
            transY <= dp(110) -> 0F
            transY > dp(110) && transY < dp(130) -> (transY - dp(110)) / dp(20)
            else -> 1F
        }
        binding.vt5.alpha = p5
        binding.vt555.alpha = p5

        effected = when {
            transY <= dp(140) -> 0F
            transY > dp(140) && transY < dp(230) -> (transY - dp(140)) / dp(90)
            else -> 1F
        }
        animViews().forEach { it.stateRefresh(R.id.vs1, R.id.vs2, effected) }
    }

    fun switch(expanded: Boolean, byScrollerSlide: Boolean = false) {
        if (isExpanded == expanded) {
            return
        }

        binding.layScroll.scrollTo(0, 0)

        isExpanded = expanded // 目标
        val start = effected
        val end = if (expanded) 1F else 0F
        statesChangeByAnimation(
            animViews(), R.id.vs1, R.id.vs2, start, end,
            null, if (!byScrollerSlide) internalAnimListener else null, 300
        )

        laySettle?.switch(isExpanded)
    }


    var animListener: AnimatorListenerAdapter1? = null
    private val internalAnimListener: AnimatorListenerAdapter = object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            animListener?.onAnimationStart(animation, isExpanded)
        }
    }

    interface AnimatorListenerAdapter1 {
        fun onAnimationStart(animation: Animator?, toExpanded: Boolean)
    }
}