package com.example.common_base.base

import android.graphics.Rect
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow


abstract class BasePopupWindow : PopupWindow(
    ViewGroup.LayoutParams.WRAP_CONTENT,
    ViewGroup.LayoutParams.WRAP_CONTENT
) {

    abstract fun onCreatePopupView(): View


    //不用角的用这个
    //leftOff: popup最左边到anchorView最左边的距离
    open fun show(anchorView: View, leftOff: Int, topOff: Int) {
        anchorView.post {
            val location = IntArray(2)
            anchorView.getLocationInWindow(location)
            val anchorX: Int = location[0]
            val anchorY: Int = location[1]
//            val windowHeight = contentView.measuredHeight
//            val windowWidth = contentView.measuredWidth
            val x = anchorX - leftOff
            val y = anchorY + anchorView.height + topOff
//            popup.updateAngleX(angleX)
            showAtLocation(anchorView, Gravity.TOP or Gravity.LEFT, x, y)
        }
    }


    override fun showAsDropDown(anchor: View) {
        if (Build.VERSION.SDK_INT >= 24) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor)
    }
}
