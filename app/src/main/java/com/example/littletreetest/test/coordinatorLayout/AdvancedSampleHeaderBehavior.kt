package com.example.littletreetest.test.coordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout


class  AdvancedSampleHeaderBehavior<T: View>(context: Context?, attrs: AttributeSet?) :

    CoordinatorLayout.Behavior<T>(
        context,
        attrs
    ) {


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: T,
        layoutDirection: Int
    ): Boolean {
        layoutChild(parent, child, layoutDirection)
        if (viewOffsetHelper == null) {
            viewOffsetHelper = ViewOffsetHelper(child)
        }
        viewOffsetHelper!!.onViewLayout()
        viewOffsetHelper!!.applyOffsets()
        if (tempTopBottomOffset != 0) {
            viewOffsetHelper!!.topAndBottomOffset = tempTopBottomOffset
            tempTopBottomOffset = 0
        }
        if (tempLeftRightOffset != 0) {
            viewOffsetHelper!!.leftAndRightOffset = tempLeftRightOffset
            tempLeftRightOffset = 0
        }
        return true
    }


    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: T,
        ev: MotionEvent
    ): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: T,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: T,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        if (target is NestedScrollView) {

            var finalY = child.translationY - dy
            //上拉直至Header消失
            if (dy > 0 && finalY > -child.height.toFloat()) {
                child.translationY = finalY
                consumed[1] = dy
            }
            //下拉scrollview直到顶部后，显示Header
            else if (dy < 0 && target.scrollY == 0) {
                if (finalY < -child.height) {
                    finalY = -child.height.toFloat()
                } else if (finalY > 0) {
                    finalY = 0f
                }
                child.translationY = finalY
                consumed[1] = dy
            }
        }

        stopNestedScrollIfNeeded(dy, child, target, type)
    }


    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: T,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        stopNestedScrollIfNeeded(dyUnconsumed, child, target, type)
    }


    private fun stopNestedScrollIfNeeded(
        dy: Int,
        child: T,
        target: View,
        type: Int
    ) {
        if (type == ViewCompat.TYPE_NON_TOUCH) {
            val currOffset: Int = getTopAndBottomOffset()
            if (dy < 0 && currOffset == 0 || dy > 0 && currOffset == -child.height) {
                ViewCompat.stopNestedScroll(target, ViewCompat.TYPE_NON_TOUCH)
            }
        }
    }




    private var viewOffsetHelper: ViewOffsetHelper? = null

    private var tempTopBottomOffset = 0
    private var tempLeftRightOffset = 0


    protected fun layoutChild(
        parent: CoordinatorLayout, child: View, layoutDirection: Int
    ) {
        parent.onLayoutChild(child, layoutDirection)
    }

    fun setTopAndBottomOffset(offset: Int): Boolean {
        tempTopBottomOffset = if (viewOffsetHelper != null) {
            return viewOffsetHelper!!.setTopAndBottomOffset(offset)
        } else {
            offset
        }
        return false
    }

    fun setLeftAndRightOffset(offset: Int): Boolean {
        tempLeftRightOffset = if (viewOffsetHelper != null) {
            return viewOffsetHelper!!.setLeftAndRightOffset(offset)
        } else {
            offset
        }
        return false
    }

    fun getTopAndBottomOffset(): Int {
        return if (viewOffsetHelper != null) viewOffsetHelper!!.getTopAndBottomOffset() else 0
    }

    fun getLeftAndRightOffset(): Int {
        return if (viewOffsetHelper != null) viewOffsetHelper!!.getLeftAndRightOffset() else 0
    }

    fun setVerticalOffsetEnabled(verticalOffsetEnabled: Boolean) {
        if (viewOffsetHelper != null) {
            viewOffsetHelper!!.setVerticalOffsetEnabled(verticalOffsetEnabled)
        }
    }

    fun isVerticalOffsetEnabled(): Boolean {
        return viewOffsetHelper != null && viewOffsetHelper!!.isVerticalOffsetEnabled()
    }

    fun setHorizontalOffsetEnabled(horizontalOffsetEnabled: Boolean) {
        if (viewOffsetHelper != null) {
            viewOffsetHelper!!.setHorizontalOffsetEnabled(horizontalOffsetEnabled)
        }
    }

    fun isHorizontalOffsetEnabled(): Boolean {
        return viewOffsetHelper != null && viewOffsetHelper!!.isHorizontalOffsetEnabled()
    }

}