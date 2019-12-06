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

    override fun convert(holder: BaseViewHolder, item: MultiItemEntity) {
        when (holder.itemViewType) {
            TYPE_LEVEL_ROLE_ATTR -> {
//                when (holder.layoutPosition % 3) {
//                    0 -> holder.setImageResource(R.id.iv_head, R.mipmap.head_img0)
//                    1 -> holder.setImageResource(R.id.iv_head, R.mipmap.head_img1)
//                }
                val lv0 = item as Level0Item
                holder.setText(R.id.tv_lv0, lv0.title)
//                val headView: View = layoutInflater.inflate(R.layout.item_head_view, null, false)
//                        .setImageResource(R.id.iv, if (lv0.isExpanded) R.mipmap.arrow_b else R.mipmap.arrow_r)
                holder.itemView.setOnClickListener {
                    val pos = holder.adapterPosition
//                    Logger.d(TAG, "Level 0 item pos: " + pos)
                    if (lv0.isExpanded) {
                        collapse(pos)
                    } else {
                        expand(pos)
                    }
                }
//                val view: View = layoutInflater.inflate(R.layout.head_view, mRecyclerView.parent as ViewGroup, false)
//
//                val headerView = getHeaderView(0, View.OnClickListener { headerAndFooterAdapter.addHeaderView(getHeaderView(1, removeHeaderListener), 0) })
//                headerAndFooterAdapter.addHeaderView(headerView)
            }

            TYPE_LEVEL_ROLE_ATTR_CONCRETE -> {
                val lv1 = item as Level1Item
                holder.setText(R.id.tv_lv1, lv1.title)
                holder.itemView.setOnClickListener {
                    val pos = holder.adapterPosition
                    Log.d(TAG, "Level 1 item pos: " + pos + "___itemtag:"+ holder.itemView.tag)


                }
//                holder.itemView.id
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

    companion object {
        private val TAG = ExpandableItemAdapter::class.java.simpleName

        val TYPE_LEVEL_ROLE_ATTR = 0
        val TYPE_LEVEL_ROLE_ATTR_CONCRETE = 1
//        val TYPE_PERSON = 2
    }
}
