package com.example.littletreetest.pages

import android.os.Bundle
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseActivity
import com.example.littletreetest.pages.free.FreeRepo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : BaseActivity() {


    @Inject
    lateinit var repo: FreeRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.container,
//                FreeFragment()
//            )
//            .commitNow()

        Timber.d(repo.toString())
    }

}
