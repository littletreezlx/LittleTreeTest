package com.example.littletreetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.allen.kotlinapp.entity.Level0Item
import com.allen.kotlinapp.entity.Level1Item
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        val list = generateData()
        val adapter = ExpandableItemAdapter(list)
        val manager = GridLayoutManager(context, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_LEVEL_ROLE_ATTR_CONCRETE) 1 else manager.spanCount
            }
        }
        rv.adapter = adapter
        rv.layoutManager = manager
        adapter.expandAll()


    }



    private fun generateData(): ArrayList<MultiItemEntity> {
//        val lv0Count = 9
//        val lv1Count = 3
//        val personCount = 5
        val attrList = arrayOf("属性", "专精", "技能")
        val nameList = arrayOf("力量", "冥界", "治理", "洞察")
        val random = Random()
        val res = ArrayList<MultiItemEntity>()
        for (i in 0 until attrList.size) {
            val lv0 = Level0Item("headview___"+attrList[i])
            for (j in 0 until nameList.size) {
                lv0.addSubItem(Level1Item(nameList[j]))
            }
            res.add(lv0)
        }
        return res
    }
}