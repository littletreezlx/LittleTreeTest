package com.example.littletreetest.pages.free

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.example.littletreetest.databinding.ItemFreeBinding
import org.jetbrains.annotations.NotNull


class FreeAdapter(list: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.item_free,
        list
    ) {


//    override fun onItemViewHolderCreated(@NotNull viewHolder: BaseViewHolder, viewType: Int) {
//        ItemFreeBinding.bind(viewHolder.itemView)
//    }


    override fun convert(holder: BaseViewHolder, item: String) {
        ItemFreeBinding.bind(holder.itemView).run {
            tvTest.text = item
        }

//        holder.run {
//            setText(R.id.tv_test, item)
//        }
    }
}