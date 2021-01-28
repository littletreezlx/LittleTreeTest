package com.example.littletreetest.pages.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.databinding.ItemRecyclerviewTestBinding
import timber.log.Timber


class TestAdapter(list: MutableList<RvItemDataModel>) :
    BaseBindingAdapter<RvItemDataModel, ItemRecyclerviewTestBinding>(
        list
    ) {

    override fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VBViewHolder<ItemRecyclerviewTestBinding> {
        Timber.d("onCreateDefViewHolder")
        return super.onCreateDefViewHolder(parent, viewType)
    }

    override fun convert(holder: VBViewHolder<ItemRecyclerviewTestBinding>, item: RvItemDataModel) {
        Timber.d("convert___position: ${holder.adapterPosition}")
        holder.binding.run {
            tvContent.text = item.content
        }
    }


    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemRecyclerviewTestBinding {
        return ItemRecyclerviewTestBinding.inflate(inflater, parent, false)
    }


}