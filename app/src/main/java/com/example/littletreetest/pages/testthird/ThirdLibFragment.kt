package com.example.littletreetest.pages.testthird

import android.os.Bundle
import android.view.View
import com.example.common_base.base.BaseDialogFragment
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.viewBinding
import com.example.littletreetest.R
import com.example.littletreetest.databinding.FragmentThirdLibBinding
import com.example.littletreetest.pages.testthird.smartrefreshlayout.SmartRefreshFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ThirdLibFragment : BaseDialogFragment() {


    override val layoutId = R.layout.fragment_third_lib

    private val binding: FragmentThirdLibBinding by viewBinding()

    private val contentAdapter by lazy {
        ThirdLibAdapter()
    }


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.rvContent.run {
            adapter = contentAdapter
        }
        contentAdapter.setList(
            listOf(
                "Curtain",
                "SmartRefresh"
            )
        )
        contentAdapter.setOnItemClickListener { adapter, view, position ->
            if (position == 1) {
                SmartRefreshFragment().show(childFragmentManager)
            }
        }
    }


}
