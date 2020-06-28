package com.example.littletreetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.littletreetest.base.BaseActivity
import com.example.littletreetest.test.coordinatorLayout.CoordinatorLayoutFragment
import com.example.littletreetest.test.scrollviewandviewpager2.ScrollViewAndViewPager2Fragment

class MainActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScrollViewAndViewPager2Fragment())
            .commitNow()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, CoordinatorLayoutFragment())
//            .commitNow()
    }

    override fun initInjector() {
    }

}
