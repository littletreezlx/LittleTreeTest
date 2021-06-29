package com.example.common_base.views.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import timber.log.Timber

class GridLayoutDecoration(
    //列数
    private val spanCount: Int,
    //x轴间隔
    private val xSpace: Int,
    //y轴间隔
    private val ySpace: Int

) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //这里是关键，需要根据你有几列来判断
        val position = parent.getChildAdapterPosition(view)
        Timber.d("$position")
        val column = position % spanCount

        //一行中最后一个不加右边距
        if (column == spanCount) {

        } else {
            outRect.right = xSpace
        }

//        outRect.left = column * xSpace / spanCount // column * ((1f / spanCount) * spacing)
//        outRect.right = xSpace - (column + 1) * xSpace / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        //第一行不加上边距
        if (position >= spanCount) {
            outRect.top = ySpace
        }
    }

}