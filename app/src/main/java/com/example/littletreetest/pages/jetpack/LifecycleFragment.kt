package com.example.littletreetest.pages.jetpack

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.viewBinding
import com.example.littletreetest.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifecycleFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_test


    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

}
