package com.example.littletreetest.pages.ui.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.pages.ui.recyclerview.node.FirstNode
import com.example.littletreetest.pages.ui.recyclerview.node.NodeTreeAdapter
import com.example.littletreetest.pages.ui.recyclerview.node.SecondNode
import com.example.littletreetest.pages.ui.recyclerview.node.ThirdNode

class NodeAdapterFragment : BaseFragment() {


    private val treeAdapter: NodeTreeAdapter =
        NodeTreeAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    private fun generateData(): MutableList<BaseNode> {
        val third = ThirdNode("third")
        val second = SecondNode(listOf(third), "second")
        val first = FirstNode(listOf(second), "first")
        first.isExpanded = true
        second.isExpanded = true
        val third2 = ThirdNode("third")
        val third1 = ThirdNode("third")
        val second2 = SecondNode(listOf(third1, third2), "second")
        val first2 = FirstNode(listOf(second2), "first")
        val res = mutableListOf<BaseNode>(first, first2)
        first2.isExpanded = true
        second2.isExpanded = true
        return res
    }

}
