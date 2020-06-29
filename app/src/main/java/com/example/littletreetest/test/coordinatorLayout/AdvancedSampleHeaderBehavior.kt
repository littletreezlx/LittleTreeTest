package com.example.littletreetest.test.coordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import timber.log.Timber

class AdvancedSampleHeaderBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<TextView>(
        context,
        attrs
    ) {

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: TextView,
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
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        if (target is NestedScrollView) {

//            Timber.d("Coordinator: Head: Y: ${finalY}, ty: ${child.translationY} ,dy: ${dy}")
            val test = target.get(0)
            target.run {
                Timber.d("Coordinator: ScrollView: Height: ${height}, max: ${maxScrollAmount},scrollY: ${scrollY}")
            }




            var finalY = child.translationY - dy

            //上拉直至Header消失
            if (dy > 0 &&  finalY > -child.height.toFloat()) {
                Timber.d("上拉直至Header消失")
                // 让CoordinatorLayout消费滑动事件
//                var finalY = child.translationY - dy
//                if (finalY < -child.height) {
//                    finalY = -child.height.toFloat()
//                } else if (finalY > 0) {
//                    finalY = 0f
//                }
                child.translationY = finalY
                consumed[1] = dy
            }
            // 上拉scrollview直到最后
            else if (dy > 0 && target.scrollY < target.maxScrollAmount) {
                Timber.d("上拉scrollview直到最后")
            }
            // 下拉scrollview直到顶部
            else if (dy < 0 && target.scrollY > 0) {
                Timber.d("下拉scrollview直到顶部")
            }
            //下拉scrollview直到顶部后，显示Header
            else if (dy < 0 && target.scrollY == 0) {
                Timber.d("下拉scrollview直到顶部后，显示Header")
                var finalY = child.translationY - dy
                if (finalY < -child.height) {
                    finalY = -child.height.toFloat()
                } else if (finalY > 0) {
                    finalY = 0f
                }
                child.translationY = finalY
                consumed[1] = dy
            }

        }
    }


    //    private boolean canScroll(View child, float scrollY) {
    //        if (scrollY > 0 && child.getTranslationY() == -child.getHeight() && !upReach) {
    //            return false;
    //        }
    //
    //        if (downReach) {
    //            return false;
    //        }
    //        return true;
    //    }
}