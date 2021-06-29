package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMainBinding
import com.example.littletreetest.pages.testthird.ThirdLibFragment
import com.example.littletreetest.pages.jetpack.LifecycleFragment
import com.example.littletreetest.pages.jetpack.flow.TestFlow0Fragment

class MainFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_main

    lateinit var binding: FragmentMainBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        binding.btnHhh.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.root, LifecycleFragment()).commitNow()
        }

//        ThirdLibFragment().show(childFragmentManager)

        TestFlow0Fragment().show(childFragmentManager)
    }


}
