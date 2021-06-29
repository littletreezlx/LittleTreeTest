package com.example.littletreetest.pages.testthird

import com.example.common_base.base.BaseBindingAdapter
import com.example.littletreetest.databinding.ItemThirdLibBinding


class ThirdLibAdapter : BaseBindingAdapter<String, ItemThirdLibBinding>(ItemThirdLibBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: String) {
        holder.getViewBinding<ItemThirdLibBinding>().run {
            tvNoteDesc.text = item
        }
    }

}