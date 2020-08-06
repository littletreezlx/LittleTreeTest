package com.example.littletreetest.test.scrollviewandviewpager2

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.example.littletreetest.base.ui.SimpleSingleSelectableAdapter

class ContentAdapter(list: MutableList<String>) :
    SimpleSingleSelectableAdapter<String>(R.layout.item_content, list) {

    override fun convert(holder: BaseViewHolder, item: String) {
        super.convert(holder, item)
        holder.run {
            setText(R.id.tv_title, item)
        }
    }
}