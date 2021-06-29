package com.example.littletreetest.pages.uicomponent.recyclerview

import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.pages.uicomponent.recyclerview.node.FirstNode
import com.example.littletreetest.pages.uicomponent.recyclerview.node.NodeTreeAdapter
import com.example.littletreetest.pages.uicomponent.recyclerview.node.SecondNode
import com.example.littletreetest.pages.uicomponent.recyclerview.node.ThirdNode

class NodeAdapterFragment : BaseFragment() {


    private val treeAdapter: NodeTreeAdapter =
        NodeTreeAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_main



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
