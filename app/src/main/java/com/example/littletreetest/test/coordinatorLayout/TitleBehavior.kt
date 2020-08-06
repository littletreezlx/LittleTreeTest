package com.example.littletreetest.test.coordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout


class TitleBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(
    context,
    attrs
) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is TextView
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var y = dependency.height + dependency.translationY
        if (y < 0) {
            y = 0f
        }
        child.y = y
        return true
    }
}