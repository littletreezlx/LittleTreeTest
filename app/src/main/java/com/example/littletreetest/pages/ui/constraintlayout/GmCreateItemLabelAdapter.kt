package com.example.littletreetest.pages.ui.constraintlayout

import android.graphics.Color
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.mixu.jingtu.common.component.SimpleMultipleSelectableAdapter

class GmCreateItemLabelAdapter(list: MutableList<String>) :
    SimpleMultipleSelectableAdapter<String>(R.layout.item_gm_create_item_label, list) {

    override fun convert(helper: BaseViewHolder, item: String) {
        super.convert(helper, item)
        helper.run {
            setText(R.id.tv_label, item)
        }
    }

    override fun convertSelected(helper: BaseViewHolder, item: String) {
        helper.run {
            setBackgroundResource(R.id.tv_label, R.drawable.background_3d4456_r10)
            setTextColor(R.id.tv_label, Color.parseColor("#FFFFFF"))
        }
    }

    override fun convertUnSelected(helper: BaseViewHolder, item: String) {
        helper.run {
            setBackgroundResource(R.id.tv_label, R.drawable.background_f3f4f5_r10)
            setTextColor(R.id.tv_label, Color.parseColor("#707070"))
        }
    }

}