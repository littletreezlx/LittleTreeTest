package com.example.littletreetest.pages

import android.os.Bundle
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseActivity
import com.example.littletreetest.pages.free.FreeFragment
import com.example.littletreetest.pages.ui.progressbar.ProgressBarFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, ScrollViewAndViewPager2Fragment())
            .replace(
                R.id.container,
//                MainFragment()
//                TestFragment()
                FreeFragment()
            )
            .commitNow()

        Timber.d(spUtil.toString())
    }

}
