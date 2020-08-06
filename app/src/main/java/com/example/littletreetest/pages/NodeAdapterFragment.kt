package com.example.littletreetest.pages

import android.os.Bundle
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

class NodeAdapterFragment : BaseFragment() {


    private val treeAdapter: NodeTreeAdapter =
        NodeTreeAdapter()

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val s = "据云南卫健委消息，近期，国家有关部门通报了从3批自厄瓜多尔进口的冻南美白虾集装箱内壁和外包装样本中检出新冠病毒核酸阳性的情况，云南省迅速组织对自厄瓜多尔进口的冻南美白虾及接触人员进行拉网式排查。截至7月14日，全省共采集2750个冻南美白虾相关样本开展新冠病毒核酸检测，其中，从包装箱外表面样本中检出3份核酸阳性，阳性样本均来自厄瓜多尔Industrial Pesquera Santa Priscila S.A（注册编号24887）生产的产品，生产批号分别为3380、3277和2616。虾体及相关接触人员核酸检测未发现阳性。\n" +
                "\n" +
                "云南省已紧急暂停采购来自厄瓜多尔的海产品，对检查中发现的厄瓜多尔3家涉事企业生产的冻南美白虾全部下架、停售、封存，对相关接触人员进行隔离观察。目前，未发现相关接触人员感染情况。所有涉及场所已封闭消杀。";

        val tiaoziUtil = TiaoZiUtil(tv_text, s, 100)
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
