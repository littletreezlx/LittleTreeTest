package com.example.littletreetest.pages.ui.progressbar

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentProgressbarBinding
import com.example.littletreetest.databinding.FragmentTestBinding
import com.example.littletreetest.databinding.FragmentViewpager2TablayoutBinding
import com.example.littletreetest.pages.ui.popupwindow.FunctionPopup
import com.example.littletreetest.pages.ui.popupwindow.StoryFilterPopup
import com.example.littletreetest.pages.ui.viewpager2tablayout.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mixu.jingtu.common.ext.dp2px
import timber.log.Timber

class ProgressBarFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_progressbar

    lateinit var binding: FragmentProgressbarBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProgressbarBinding.bind(view)

    }





}
