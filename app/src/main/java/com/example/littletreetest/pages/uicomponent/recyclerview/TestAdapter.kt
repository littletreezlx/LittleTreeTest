package com.example.littletreetest.pages.uicomponent.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.example.littletreetest.R
import com.example.littletreetest.databinding.ItemRecyclerviewTestBinding
import timber.log.Timber
import kotlin.concurrent.thread


class TestAdapter(list: MutableList<RvItemDataModel>) :
    BaseBindingAdapter<RvItemDataModel, ItemRecyclerviewTestBinding>(
        list
    ) {


    override fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VBViewHolder<ItemRecyclerviewTestBinding> {
        val holder = super.onCreateDefViewHolder(parent, viewType)
        Timber.d("onCreateDefViewHolder___adapterPosition:${holder.adapterPosition}")
        Timber.d("onCreateDefViewHolder___layoutPosition:${holder.layoutPosition}")
        return holder
    }


    override fun convert(holder: VBViewHolder<ItemRecyclerviewTestBinding>, item: RvItemDataModel) {
        Timber.d("convert___adapterPosition: ${holder.adapterPosition}")
        Timber.d("convert___layoutPosition: ${holder.layoutPosition}")
//        Timber.d("convert___viewHolder: ${holder}")
//        Timber.d("convert___viewHolder: ${holder.itemId}")
        holder.binding.run {
            tvContent.text = item.content

//            root.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim0)
//            Glide.with(context).load(R.color.white).into(holder.binding.ivTest)

            holder.itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim3)
        }
        if (holder.layoutPosition == 0) {
//            holder.itemView.setTag(1, "1")
//            Glide.with(context).load("https://scpic.chinaz.net/files/pic/pic9/202101/apic30480.jpg").into(
//                holder.binding.ivTest
//            )
            holder.itemView.setTag(R.id.tag_first, "1")

            thread {
                Thread.sleep(3000)
                val file = Glide.with(context)
                    .downloadOnly()
                    .load("https://scpic.chinaz.net/files/pic/pic9/202101/apic30480.jpg")
                    .submit()
                    .get()
//                if (holder.layoutPosition == 0) {
//                    holder.binding.ivTest.post {
//                        Glide.with(context).load(file).into(holder.binding.ivTest)
//                    }
//                }
                holder.binding.ivTest.post {
                    Glide.with(context).load(file).into(holder.binding.ivTest)
                }
            }
        }
    }


    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemRecyclerviewTestBinding {
        return ItemRecyclerviewTestBinding.inflate(inflater, parent, false)
    }


}