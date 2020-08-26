package com.example.littletreetest.pages

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentTestBinding
import com.example.littletreetest.pages.ui.popupwindow.FunctionPopup
import com.example.littletreetest.pages.ui.popupwindow.StoryFilterPopup
import com.mixu.jingtu.common.ext.dp2px
import timber.log.Timber

class TestFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    override val layoutId = R.layout.fragment_test

    lateinit var binding: FragmentTestBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTestBinding.bind(view)

        binding.tvTest.setOnClickListener {
            val popup = StoryFilterPopup(requireContext())
            popup.show(it, dp2px(220f) , 0)
        }

    }
}
