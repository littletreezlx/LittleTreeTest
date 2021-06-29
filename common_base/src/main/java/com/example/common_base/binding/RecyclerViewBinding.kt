package com.example.common_base.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import timber.log.Timber


object RecyclerViewBinding {


    @BindingAdapter("adapterList")
    fun <T> bindAdapterDataList(view: RecyclerView, adapterList: Collection<T>) {
        adapterList?.let { data ->
            view.adapter?.let {
                if (it is BaseQuickAdapter<*, *>) {
                    Timber.d("setList: ${data.size}")
                    (it as BaseQuickAdapter<T, *>).setList(data)
                }
            }
        }
    }


    @BindingAdapter("decoration")
    fun bindDecoration(view: RecyclerView, itemDecoration: ItemDecoration) {
        view.addItemDecoration(itemDecoration)
    }

}
