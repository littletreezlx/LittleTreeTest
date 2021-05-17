package com.example.littletreetest.pages.ui.viewpager2tablayout

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentViewpager2TablayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2TabLayoutFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_viewpager2_tablayout

    lateinit var binding: FragmentViewpager2TablayoutBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentViewpager2TablayoutBinding.bind(view)
        binding.viewpager.adapter = ViewPager2Adapter(mutableListOf("1", "2", "3"))

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
//            tab.setText(readRuleAdapter.getTabTitle(position))
        }.attach()
    }


}
