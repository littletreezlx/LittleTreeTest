package com.example.common_base.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.scwang.smart.refresh.layout.SmartRefreshLayout


object SmartRefreshLayoutBinding {


//    @JvmStatic
//    @BindingAdapter("smartRefreshLayout_refreshing")
//    fun setSwipeRefreshLayoutRefreshing(
//        layout: SmartRefreshLayout,
//        newValue: Boolean
//    ) {
//        if (layout.isRefreshing!= newValue){
//            if (newValue){
//                layout.autoRefresh()
//            }else{
//                layout.finishRefresh()
//            }
//        }
//
//    }


//    @JvmStatic
//    @BindingAdapter(
//        "smartRefreshLayout_refreshingAttrChanged",
////        requireAll = false
//    )
//    fun setOnRefreshListener(
//        layout: SmartRefreshLayout,
//        bindingListener: InverseBindingListener?
//    ) {
//        if (bindingListener != null)
//            layout.setOnRefreshListener {
//                bindingListener.onChange()
//            }
//    }


//    @JvmStatic
//    @InverseBindingAdapter(
//        attribute = "smartRefreshLayout_refreshing",
//        event = "smartRefreshLayout_refreshingAttrChanged"
//    )
//    fun isSmartRefreshLayoutRefreshing(layout: SmartRefreshLayout): Boolean =
//        layout.isRefreshing


}

@BindingAdapter("smartRefreshLayout_refreshing")
fun SmartRefreshLayout.setSwipeRefreshLayoutRefreshing(
    newValue: Boolean
) {
    if (isRefreshing != newValue) {
        if (newValue) {
            autoRefresh()
        } else {
            finishRefresh()
        }
    }
}

@BindingAdapter(
    "smartRefreshLayout_refreshingAttrChanged",
//        requireAll = false
)
fun SmartRefreshLayout.setOnRefreshListener(
    bindingListener: InverseBindingListener
) {
//    if (bindingListener != null)
//        setOnRefreshListener {
//            bindingListener.onChange()
//        }
    setOnRefreshListener {
        bindingListener.onChange()
    }
}


@InverseBindingAdapter(
    attribute = "smartRefreshLayout_refreshing",
    event = "smartRefreshLayout_refreshingAttrChanged"
)
fun SmartRefreshLayout.isSmartRefreshLayoutRefreshing(): Boolean = isRefreshing