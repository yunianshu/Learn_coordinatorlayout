package com.example.learn.business

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.learn.R
import com.example.learn.databinding.TicketViewBinding
import com.example.learn.view.AnimationUpdateListener
import com.example.learn.view.stateRefresh
import com.example.learn.view.stateSave
import com.example.learn.text

class TicketView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs),
    AnimationUpdateListener {

    private var firstLayout: Boolean = false
    lateinit var binding: TicketViewBinding

    init {
        binding  = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.ticket_view,this,true)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (!firstLayout) {
            firstLayout = true
            binding.apply {
                vBorder1.stateSave(R.id.vs1).a(1F)
                vBorder1.stateSave(R.id.vs2).ws(3.8F).hs(3.8F).a(0F)
                vBorder2.stateSave(R.id.vs1).a(0F)
                vBorder2.stateSave(R.id.vs2).ws(3.8F).hs(3.8F).a(1F)
                vSimple.stateSave(R.id.vs1)
                vSimple.stateSave(R.id.vs2).a(0f)
                layDetail.stateSave(R.id.vs1)
                layDetail.stateSave(R.id.vs2).sx(1F).sy(1F).a(1F)
            }
        }
    }

    fun set(amount: Int, limit: Int, expireTime: String) {
        binding.apply {
            vSimple.text("领￥$amount")
            vDetail1.text("￥$amount")
            vDetail2.text("满$limit 可用")
            vDetail3.text("有效期至$expireTime")
        }
    }

    override fun onAnimationUpdate(tag1: Int, tag2: Int, p: Float) {
        binding.apply {
            arrayOf(vBorder1, vBorder2, vSimple, layDetail).forEach {
                it.stateRefresh(
                    tag1,
                    tag2,
                    p
                )
            }
        }
    }
}