package com.example.littletreetest.pages.ui.popupwindow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class AnglePopupView(context: Context) : View(context) {


    companion object {
        const val ANGLE_ORIENTATION_DOWN = 0

        const val ANGLE_ORIENTATION_UP = 1
    }

    init {

    }


    var mHeight: Float = 100f


    var mWidth: Float = 100f


    var mRadius: Float = 0f


    var mStrokeWidth: Float = 0f


    var mStrokeColor: Int = Color.parseColor("#333333")


    var mBgColor: Int = Color.WHITE


    var angleX: Float = 0f


    var angleOrientation: Int = ANGLE_ORIENTATION_DOWN


    val angleHeight: Float = 3f


    val angleWidth: Float = 3f


    var paint = Paint()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.run {
            strokeWidth = mStrokeWidth
            color = mStrokeColor
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        val adjust = paint.strokeWidth / 2
        canvas.drawRoundRect(
            adjust,
            adjust,
            mWidth - adjust,
            mWidth - adjust,
            mRadius,
            mRadius,
            paint
        )


    }


}