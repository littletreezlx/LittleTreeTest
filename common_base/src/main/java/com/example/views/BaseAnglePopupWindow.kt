package com.example.views

import android.view.Gravity
import android.view.View
import com.example.common_base.base.BasePopupWindow
import timber.log.Timber


abstract class BaseAnglePopupWindow() : BasePopupWindow() {

    init {
        isFocusable = true
    }

    //用角的用这个
//    leftOff: popup最左边到anchorView最左边的距离
    override fun show(anchorView: View, leftOff: Int, topOff: Int) {
        anchorView.post {
            val location = IntArray(2)
            anchorView.getLocationInWindow(location)
            val anchorX: Int = location[0]
            val anchorY: Int = location[1]
//            val windowHeight = contentView.measuredHeight
//            val windowWidth = contentView.measuredWidth
            var x = anchorX - leftOff
            var y = anchorY + anchorView.height + topOff
            val apl = contentView
            if (apl is AnglePopupLayout) {
                x -= apl.mShadowBlur.toInt()
                y -= apl.mShadowBlur.toInt()
                val angleX: Float = leftOff + anchorView.width / 2f - apl.angleWidth / 2
                apl.angleX = angleX

                Timber.d("AnglePopupLayout: angleX: ${angleX}")
                apl.invalidate()
            }
            showAtLocation(anchorView, Gravity.TOP or Gravity.LEFT, x, y)
        }
    }


    fun getAnglePopupLayout() = contentView as AnglePopupLayout
}
