package com.example.littletreetest.pages

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.littletreetest.MyApp
import com.example.littletreetest.R
import com.example.littletreetest.TiaoZiUtil
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.node.FirstNode
import com.example.littletreetest.node.NodeTreeAdapter
import com.example.littletreetest.node.SecondNode
import com.example.littletreetest.node.ThirdNode
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TestFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    override val layoutId = R.layout.main_fragment


    override fun initView(view: View, savedInstanceState: Bundle?) {
        val popup = FunctionPopup(context)
        popup.setFunctionList(
            mutableListOf(
                FunctionPopup.FunctionItem("编辑") {},
                FunctionPopup.FunctionItem("删除") {}
            ))
        popup.showAsDropDown(it, -dp2px(10f), -dp2px(
            10f
        ), Gravity.LEFT or Gravity.BOTTOM)
    }
}
