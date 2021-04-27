package com.example.littletreetest.pages.jingtu.rolecard

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.mixu.jingtu.common.component.SimpleSingleSelectableAdapter

class RoleCardTitleAdapter(list: MutableList<String>) :
    SimpleSingleSelectableAdapter<String>(R.layout.item_role_card_title, list) {

    override fun convert(holder: BaseViewHolder, item: String) {
        super.convert(holder, item)
        holder.run {
            setText(R.id.tv_title, item)
        }
    }

    override fun convertSelected(holder: BaseViewHolder, item: String) {
        holder.run {
            setBackgroundResource(R.id.tv_title, R.drawable.background_item_role_card_title_selected)
            setVisible(R.id.iv_bottom_line, true)
        }
    }

    override fun convertUnSelected(holder: BaseViewHolder, item: String) {
        holder.run {
            setBackgroundResource(R.id.tv_title, R.drawable.background_item_role_card_title_unselected)
            setVisible(R.id.iv_bottom_line, false)
        }
    }
}