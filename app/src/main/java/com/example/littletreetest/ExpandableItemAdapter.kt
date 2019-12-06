package com.example.littletreetest

import android.util.Log
import com.allen.kotlinapp.entity.Level0Item
import com.allen.kotlinapp.entity.Level1Item
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * 文 件 名: ExpandableItemAdapter
 * 创 建 人: Allen
 * 创建日期: 2017/6/13 15:12
 * 修改时间：
 * 修改备注：
 */
class ExpandableItemAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.

 * @param data A new list is created out of this one to avoid mutable list
 */
(data: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(TYPE_LEVEL_ROLE_ATTR, R.layout.item_expandable_lv0)
        addItemType(TYPE_LEVEL_ROLE_ATTR_CONCRETE, R.layout.item_expandable_lv1)
    }

    var layoutPosition = -1


    override fun convert(holder: BaseViewHolder, item: MultiItemEntity) {
        Log.d("LittleTreeTest","convert: ${holder.adapterPosition}")
        when (holder.itemViewType) {
            TYPE_LEVEL_ROLE_ATTR -> {
                val lv0 = item as Level0Item
                layoutPosition++

                holder.setText(R.id.tv_lv0, lv0.title)
                holder.addOnClickListener(R.id.tv_lv0)

                holder.itemView.setOnClickListener {
                    val pos = holder.adapterPosition
                    if (lv0.isExpanded) {
                        collapse(pos)
                    } else {
                        expand(pos)
                    }
                }
//                val view: View = layoutInflater.inflate(R.layout.head_view, mRecyclerView.parent as ViewGroup, false)
//                val headerView = getHeaderView(0, View.OnClickListener { headerAndFooterAdapter.addHeaderView(getHeaderView(1, removeHeaderListener), 0) })
//                headerAndFooterAdapter.addHeaderView(headerView)
            }
            TYPE_LEVEL_ROLE_ATTR_CONCRETE -> {
                val lv1 = item as Level1Item
                lv1.parentPositionInData = layoutPosition

                holder.setText(R.id.tv_lv1, lv1.title)
                holder.addOnClickListener(R.id.tv_lv1)
                holder.addOnLongClickListener(R.id.tv_lv1)
            }

//            TYPE_PERSON -> {
//                val person = item as Person
//                holder.setText(R.id.tv, person.name + " parent pos: " + getParentPosition(person))
//                holder.itemView.setOnClickListener {
//                    val cp = getParentPosition(person)
//                    (data[cp] as Level1Item).removeSubItem(person)
//                    data.removeAt(holder.layoutPosition)
//                    notifyItemRemoved(holder.layoutPosition)
//                }
//            }
        }
    }


    override fun remove(position: Int) {
        super.remove(position)
    }


    fun getPositionInList(level1Item: Level1Item): Int{
        val s = data
        val level0 = data[level1Item.parentPositionInData] as Level0Item
        return level0.getSubItemPosition(level1Item)
    }


    companion object {
        private val TAG = ExpandableItemAdapter::class.java.simpleName

        val TYPE_LEVEL_ROLE_ATTR = 0
        val TYPE_LEVEL_ROLE_ATTR_CONCRETE = 1
//        val TYPE_PERSON = 2
    }
}
