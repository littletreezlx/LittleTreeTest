package com.example.littletreetest.pages.ui.constraintlayout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.littletreetest.R
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

class LabelTagAdapter(list: MutableList<String>) : TagAdapter<String>(list) {


    override fun getView(parent: FlowLayout, position: Int, t: String?): View {
        val tv = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gm_create_item_label_new,
            parent, false
        ) as TextView
        tv.text = t
        return tv
    }

    override fun onSelected(position: Int, view: View?) {
        super.onSelected(position, view)
        val tv = view as TextView
        tv.setBackgroundResource(R.drawable.background_3d4456_r10)
        tv.setTextColor(Color.parseColor("#FFFFFF"))
    }

    override fun unSelected(position: Int, view: View?) {
        super.unSelected(position, view)
        val tv = view as TextView
        tv.setBackgroundResource(R.drawable.background_f3f4f5_r10)
        tv.setTextColor(Color.parseColor("#707070"))
    }


//    override fun getView(parent: FlowLayout, position: Int, t: String?): View {
//        val rootView = LayoutInflater.from(parent.context).inflate(
//            R.layout.item_gm_create_item_label,
//            parent, true
//        )
//        val tv = rootView.findViewById<TextView>(R.id.tv_label)
//        tv.text = t
//        return tv
//    }
//
//    override fun onSelected(position: Int, view: View) {
//        super.onSelected(position, view)
//        val tv = view.findViewById<TextView>(R.id.tv_label)
//        tv.setBackgroundResource(R.drawable.background_3d4456_r10)
//        tv.setTextColor(Color.parseColor("#FFFFFF"))
//    }
//
//    override fun unSelected(position: Int, view: View) {
//        super.unSelected(position, view)
//        val tv = view.findViewById<TextView>(R.id.tv_label)
//        tv.setBackgroundResource(R.drawable.background_f3f4f5_r10)
//        tv.setTextColor(Color.parseColor("#707070"))
//    }
}