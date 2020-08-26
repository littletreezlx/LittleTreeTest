package com.example.littletreetest.pages.ui.popupwindow

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.littletreetest.R
import com.example.littletreetest.base.ui.SimpleSingleSelectableAdapter
import com.example.littletreetest.databinding.PopupStoryFilterBinding
import com.mixu.jingtu.common.component.GridLayoutDecoration
import com.mixu.jingtu.common.ext.dp2px
import razerdp.basepopup.BasePopup
import timber.log.Timber


abstract class BaseAnglePopupWindow() : BasePopupWindow() {

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
                Timber.d("AnglePopupLayout: HHHH")
                x -= apl.mShadowBlur.toInt()
                y -= apl.mShadowBlur.toInt()
                val angleX:Float = leftOff + anchorView.width / 2f - apl.angleWidth /2
                apl.angleX = angleX
                apl.invalidate()
            }
            showAtLocation(anchorView, Gravity.TOP or Gravity.LEFT, x, y)
        }
    }


}
