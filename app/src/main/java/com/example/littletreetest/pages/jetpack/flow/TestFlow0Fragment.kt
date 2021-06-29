package com.example.littletreetest.pages.jetpack.flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common_base.base.BaseDialogFragment
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.viewBinding
import com.example.littletreetest.R
import com.example.littletreetest.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFlow0Fragment : BaseDialogFragment() {


    override val layoutId = R.layout.fragment_test

    private val binding: FragmentTestBinding by viewBinding()

    private val vm: TestFlow0ViewModel by viewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        vm.flowValue.observe(viewLifecycleOwner) {
            binding.tvTest.text = it
        }
        binding.tvTest.setOnClickListener {
            vm.test()
        }
    }


}
