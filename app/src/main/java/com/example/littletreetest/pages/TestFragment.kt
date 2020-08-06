package com.example.littletreetest.pages

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentTestBinding
import com.example.littletreetest.pages.ui.popupwindow.FunctionPopup
import com.mixu.jingtu.common.ext.dp2px

class TestFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    override val layoutId = R.layout.fragment_test

    lateinit var binding: FragmentTestBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTestBinding.bind(view)


        binding.btnTest.setOnClickListener {
            val popup =
                FunctionPopup(
                    context
                )
            popup.setFunctionList(
                mutableListOf(
                    FunctionPopup.FunctionItem("编辑") {},
                    FunctionPopup.FunctionItem("删除") {},
                    FunctionPopup.FunctionItem("哈哈") {}
                ))
            popup.showAsDropDown(
                it, dp2px(10f), dp2px(10f), Gravity.LEFT or Gravity.BOTTOM
            )
        }

    }
}
