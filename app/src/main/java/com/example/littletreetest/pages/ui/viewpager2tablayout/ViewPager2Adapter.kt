package com.example.littletreetest.pages.ui.viewpager2tablayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.mixu.jingtu.common.component.SimpleSingleSelectableAdapter



class ViewPager2Adapter(list: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_viewpager2, list) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.run {
            setText(R.id.tv_text, item)
        }
    }
}