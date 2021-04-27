package com.example.littletreetest.pages

import android.os.Bundle
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseActivity
import com.example.littletreetest.pages.free.FreeFragment
import com.example.littletreetest.pages.free.FreeRepo
import com.example.littletreetest.pages.jingtu.DianzanFragment
import com.example.littletreetest.pages.ui.progressbar.ProgressBarFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var repo: FreeRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
