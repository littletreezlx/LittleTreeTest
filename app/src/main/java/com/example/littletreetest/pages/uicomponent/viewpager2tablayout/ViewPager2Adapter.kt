package com.example.littletreetest.pages.uicomponent.viewpager2tablayout

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R


class ViewPager2Adapter(list: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_viewpager2, list) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.run {
            setText(R.id.tv_text, item)
        }
    }
}