package com.example.littletreetest

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import razerdp.basepopup.BasePopupWindow


class TestPopup(context: Context?) : BasePopupWindow(context) {

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.test_popup)
    }

    override fun onViewCreated(contentView: View?) {
        val popup = TestPopup2(context)
        popup.popupGravity = Gravity.CENTER

//        popup.showPopupWindow(R.id.tv_desc)
//        showToast("onViewCreated")

    }





    override fun onCreateShowAnimation(): Animation? {
        val showAnimation: Animation = ScaleAnimation(0f, 1f, 0f, 1f)
        showAnimation.setDuration(500)
        return showAnimation
    }

    override fun onCreateDismissAnimation(): Animation? {
        val showAnimation: Animation = ScaleAnimation(1f, 0f, 1f, 0f)
        showAnimation.setDuration(500)
        return showAnimation
    }
}