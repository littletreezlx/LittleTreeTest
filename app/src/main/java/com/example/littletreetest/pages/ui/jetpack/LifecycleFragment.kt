package com.example.littletreetest.pages.ui.jetpack

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentTestBinding
import com.mixu.jingtu.common.ext.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LifecycleFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_test

    private val binding: FragmentTestBinding by viewBinding()


    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

}
