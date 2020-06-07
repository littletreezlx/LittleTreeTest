package com.example.littletreetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.littletreetest.node.FirstNode
import com.example.littletreetest.node.NodeTreeAdapter
import com.example.littletreetest.node.SecondNode
import com.example.littletreetest.node.ThirdNode
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.random.Random

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

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

        val vm =  ViewModelProvider(activity as ViewModelStoreOwner).get(ListViewModel::class.java)
        btn_show_toast.setOnClickListener {
            val random = Random.nextInt(100)
            vm.updateStr(random.toString())
        }
        vm._AllStr.observe(viewLifecycleOwner, Observer {
            tv_all_str.text = it
        })
        vm.subStr.observe(viewLifecycleOwner, Observer {
            tv_sub_str.text = it
        })



        rv_list.run {
            setLayoutManager(LinearLayoutManager(context))
            setAdapter(treeAdapter)

        }
        treeAdapter.setList(generateData())
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


fun showToast(message: String) {
    Toast.makeText(MyApp.context, message, Toast.LENGTH_SHORT).show()
}
