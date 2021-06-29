package com.example.common_base.utils

import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.blankj.utilcode.util.KeyboardUtils


object KeyboardUtil {


    fun hideKeyboardWhenTouchOutEditText(
        event: MotionEvent,
        view: View?,
    ) {
        try {
            if (view != null && view is EditText) {
                val location = intArrayOf(0, 0)
                view.getLocationInWindow(location)
                val left = location[0]
                val top = location[1]
                val right = (left
                        + view.getWidth())
                val bootom = top + view.getHeight()
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (
                    event.rawX < left
                    || event.rawX > right
                    || event.rawY < top
                    || event.rawY > bootom
                ) {
                    // 隐藏键盘
//                    val token: IBinder = view.getWindowToken()
//                    val inputMethodManager = activity
//                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                    inputMethodManager.hideSoftInputFromWindow(
//                        token,
//                        InputMethodManager.HIDE_NOT_ALWAYS
//                    )
                    KeyboardUtils.hideSoftInput(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}