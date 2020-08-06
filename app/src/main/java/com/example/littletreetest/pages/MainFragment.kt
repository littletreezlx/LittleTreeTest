package com.example.littletreetest.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

class MainFragment : BaseFragment() {

    override val layoutId = R.layout.main_fragment


    override fun initView(view: View, savedInstanceState: Bundle?) {
        findNavController().navigate(R.id.action_mainFragment_to_testFragment)
    }

}
