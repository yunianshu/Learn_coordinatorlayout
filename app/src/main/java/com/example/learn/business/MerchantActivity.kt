package com.example.learn.business

import android.os.Bundle
import com.example.learn.BaseActivity
import com.example.learn.R
import com.example.learn.log

class MerchantActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_activity)
        initialView()
        log("MerchantActivity onCreate")
    }

    private fun initialView() {
//        findViewById<MerchantContentLayout>(R.id.layContent).laySettle = findViewById(R.id.laySettle)
    }
}
