package com.example.littletreetest.pages

import android.os.Bundle
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, ScrollViewAndViewPager2Fragment())
            .replace(
                R.id.container,
                MainFragment()
            )
            .commitNow()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, CoordinatorLayoutFragment())
//            .commitNow()
    }

    override fun initInjector() {
    }

}
