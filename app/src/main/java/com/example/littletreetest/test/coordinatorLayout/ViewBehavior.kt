package com.example.littletreetest.test.coordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

class ViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(
    context,
    attrs
) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is RecyclerView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        //计算列表y坐标，最小为0
        var y = dependency.height + dependency.translationY
//        Timber.d("Coordinator: Head: Height: ${dependency.height}")
        val scrollHeight = child.height
//        val titleHeight = ConvertUtils.dp2px(40f).toFloat()
//        if (y < titleHeight) {
//            y = titleHeight
//        }
        child.y = y
        return true
    }
}