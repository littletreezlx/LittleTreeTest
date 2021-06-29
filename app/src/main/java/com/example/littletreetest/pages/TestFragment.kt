package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common_base.base.BaseDialogFragment
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.viewBinding
import com.example.littletreetest.databinding.FragmentTestBinding
import com.example.littletreetest.pages.jetpack.flow.TestFlow0ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : BaseDialogFragment() {


    override val layoutId = R.layout.fragment_test

    private val binding: FragmentTestBinding by viewBinding()

    private val vm: TestViewModel by viewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {

    }


}
