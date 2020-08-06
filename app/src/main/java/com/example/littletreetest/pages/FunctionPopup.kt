package com.example.littletreetest.pages

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R

class FunctionPopup(context: Context?) : PopupWindow() {


    init {
        width = RelativeLayout.LayoutParams.WRAP_CONTENT
        height = RelativeLayout.LayoutParams.WRAP_CONTENT
        isFocusable = true
        contentView = getView(context)
    }

    private lateinit var functionAdapter: FunctionAdapter


    private fun getView(context: Context?): View {
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_function, null)
        functionAdapter = FunctionAdapter(mutableListOf())
        popupView.findViewById<RecyclerView>(R.id.rv_function).run {
            layoutManager = LinearLayoutManager(context)
            adapter =functionAdapter
        }
        functionAdapter.setOnItemClickListener { adapter, view, position ->
            val functionItem  = functionAdapter.getItem(position)
            functionItem.function()
        }
        return popupView
    }


    fun setFunctionList(functionList: MutableList<FunctionItem>) {
        functionAdapter.setList(functionList)
    }



    class FunctionItem(val text: String, var function: () -> Unit)


    class FunctionAdapter(list: MutableList<FunctionItem>) : BaseQuickAdapter<FunctionItem, BaseViewHolder>(R.layout.item_popup_item_function, list) {

        override fun convert(helper: BaseViewHolder, item: FunctionItem) {
            helper.run {
                setText(R.id.btn_function, item.text)
            }
        }
    }


}
