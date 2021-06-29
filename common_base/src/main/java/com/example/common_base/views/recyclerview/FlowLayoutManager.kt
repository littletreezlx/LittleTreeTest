package com.example.common_base.views.recyclerview

import android.content.Context
import android.graphics.Rect
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler


class FlowLayoutManager(context: Context?, private val mIsFullyLayout: Boolean) :
    RecyclerView.LayoutManager() {
    private val TAG = this.javaClass.name
    private val cachedViews: SparseArray<View> = SparseArray()
    private val layoutPoints: SparseArray<Rect> = SparseArray()
    private var totalWidth = 0
    private var totalHeight = 0
    private var mContentHeight = 0
    private var mOffset = 0
    override fun supportsPredictiveItemAnimations(): Boolean {
        return true
    }

//    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
//        for (i in 0 until itemCount) {
//            val v: View = cachedViews.get(i)
//            val rect: Rect = layoutPoints.get(i)
//            layoutDecorated(v, rect.left, rect.top, rect.right, rect.bottom)
//        }
//    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: Recycler, state: RecyclerView.State): Int {
        return dx
    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler, state: RecyclerView.State): Int {
        var shouldOffset = 0
        if (mContentHeight - totalHeight > 0) {
            var targetOffset = mOffset + dy
            if (targetOffset < 0) {
                targetOffset = 0
            } else if (targetOffset > mContentHeight - totalHeight) {
                targetOffset = mContentHeight - totalHeight
            }
            shouldOffset = targetOffset - mOffset
            offsetChildrenVertical(-shouldOffset)
            mOffset = targetOffset
        }
        if (mIsFullyLayout) {
            shouldOffset = dy
        }
        return shouldOffset
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return true
    }


    override fun onAdapterChanged(
        oldAdapter: RecyclerView.Adapter<*>?,
        newAdapter: RecyclerView.Adapter<*>?
    ) {
        removeAllViews()
    }


    override fun onMeasure(
        recycler: Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
        val widthMode: Int = View.MeasureSpec.getMode(widthSpec)
        val heightMode: Int = View.MeasureSpec.getMode(heightSpec)
        val widthSize: Int = View.MeasureSpec.getSize(widthSpec)
        val heightSize: Int = View.MeasureSpec.getSize(heightSpec)
        var height: Int
        when (widthMode) {
            View.MeasureSpec.UNSPECIFIED -> {
//                Log.d(TAG, "WidthMode is unspecified.")
            }
            View.MeasureSpec.AT_MOST -> {
            }
            View.MeasureSpec.EXACTLY -> {
            }
        }
        removeAndRecycleAllViews(recycler)
        recycler.clear()
        cachedViews.clear()
        mContentHeight = 0
        totalWidth = widthSize - paddingRight - paddingLeft
        var left = paddingLeft
        var top = paddingTop
        var maxTop = top
        for (i in 0 until itemCount) {
            val v: View = recycler.getViewForPosition(i)
            addView(v)
            measureChildWithMargins(v, 0, 0)
            cachedViews.put(i, v)
        }
        for (i in 0 until itemCount) {
            val v: View = cachedViews.get(i)
            val w = getDecoratedMeasuredWidth(v)
            val h = getDecoratedMeasuredHeight(v)
            if (w > totalWidth - left) {
                left = paddingLeft
                top = maxTop
            }
            val rect = Rect(left, top, left + w, top + h)
            layoutPoints.put(i, rect)
            left = left + w
            if (top + h >= maxTop) {
                maxTop = top + h
            }
        }
        mContentHeight = maxTop - paddingTop
        height = mContentHeight + paddingTop + paddingBottom
        when (heightMode) {
            View.MeasureSpec.EXACTLY -> height = heightSize
            View.MeasureSpec.AT_MOST -> if (height > heightSize) {
                height = heightSize
            }
            View.MeasureSpec.UNSPECIFIED -> {
            }
        }
        totalHeight = height - paddingTop - paddingBottom
        setMeasuredDimension(widthSize, height)
    }
}