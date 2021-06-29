package com.example.littletreetest.pages

import android.os.Bundle
import com.example.common_base.base.BaseActivity
import com.example.littletreetest.R
import com.example.littletreetest.pages.free.FreeRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var repo: FreeRepo

    override fun initView(savedInstanceState: Bundle?) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
