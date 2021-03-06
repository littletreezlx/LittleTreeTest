package com.example.littletreetest.test.coordinatorLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;

public class ScrollViewBehavior extends CoordinatorLayout.Behavior<NestedScrollView> {

    public ScrollViewBehavior() {
    }

    public ScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, NestedScrollView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, NestedScrollView child, View dependency) {
        //计算列表y坐标，最小为0
        float y = dependency.getHeight() + dependency.getTranslationY();
        if (y < 0) {
            y = 0;
        }
        child.setY(y);
        return true;
    }
}