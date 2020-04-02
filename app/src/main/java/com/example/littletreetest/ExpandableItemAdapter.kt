package com.example.littletreetest

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class ExpandableItemAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main) {


    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_item, item)
    }

}
