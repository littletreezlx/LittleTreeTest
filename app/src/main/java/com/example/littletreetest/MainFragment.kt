package com.example.littletreetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.allen.kotlinapp.entity.Level0Item
import com.allen.kotlinapp.entity.Level1Item
import com.chad.library.adapter.base.BaseQuickAdapter
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
        val testAdapter = ExpandableItemAdapter(list)
        val manager = GridLayoutManager(context, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (testAdapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_LEVEL_ROLE_ATTR_CONCRETE) 1 else manager.spanCount
            }
        }
        rv.adapter = testAdapter
        rv.layoutManager = manager
        testAdapter.expandall


        testAdapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_lv0 -> {
                    showToast("its lv0")
                }
                R.id.tv_lv1 -> {
                    val item = adapter.getItem(position) as Level1Item
                    showToast(
                        """ 
                            position: $position
                            parentPositionInData: ${item.parentPositionInData}
                        """
                    )
                }
            }
        }

        testAdapter.onItemChildLongClickListener = BaseQuickAdapter.OnItemChildLongClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_lv0 -> {
                    showToast("long lv0")
                }
                R.id.tv_lv1 -> {
                    val item1 = adapter.getItem(position) as Level1Item

//                    val pp = adapter.getParentPosition(item1)
//                    val item0 = adapter.getItem(pp) as Level0Item
//                    val ppp = item0.getSubItemPosition(item1)

                    showToast(""""long lv1 remove ————
                        parentPositionInData:${item1.parentPositionInData}
                        getPositionInList: ${testAdapter.getPositionInList(item1)}
                        """
                        .trimMargin())

                    adapter.remove(position)
//                    adapter.data.removeAt(position)
                    adapter.notifyItemChanged(position)
                }
            }
            true
        }
    }


    fun aaaaa(){
        val list = arrayListOf<String>("a", "b", "c")
        list.indexOf("a")

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

fun showToast(message: String){
    Toast.makeText(MyApp.context, message, Toast.LENGTH_SHORT).show()
}
