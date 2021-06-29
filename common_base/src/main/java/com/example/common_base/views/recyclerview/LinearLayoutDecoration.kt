package com.example.common_base.views.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class LinearLayoutDecoration(
    private val space: Int,
    private val orientation: Int = RecyclerView.VERTICAL,
    private val reverseLayout: Boolean = false
) :
    ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {

        if (parent.getChildAdapterPosition(view) == 0) {
        } else {

            if (orientation == RecyclerView.VERTICAL) {
                outRect.top = space
            } else {
                outRect.left = space
            }
        }
    }

}