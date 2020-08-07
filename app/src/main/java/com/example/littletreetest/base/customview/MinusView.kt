package com.mixu.jingtu.common.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.littletreetest.R

class MinusView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var mPaint: Paint = Paint()
    var HstartX = 0f
    var HstartY = 0f
    var HendX = 0f
    var HendY= 0f
    //水平的线

    //垂直的线
    var SstartX = 0f
    var SstartY = 0f
    var SsendX = 0f
    var SsendY = 0f
    var mPaintWidth = 2f //初始化加号的粗细为10
    var mPaintColor: Int = Color.BLACK //画笔颜色黑色

    var mPadding = 0f //默认3的padding


    init {
        initView(context, attrs)
    }


    fun getPadding(): Float {
        return mPadding
    }

    //让外界调用，修改padding的大小
    fun setPadding(padding: Float) {
        HendX = width - padding
        SsendY = HendX
        HstartX = padding
        SstartY = HstartX
    }

    //让外界调用，修改加号颜色
    fun setPaintColor(paintColor: Int) {
        mPaint.color = paintColor
    }

    //让外界调用，修改加号粗细
    fun setPaintWidth(mPaintWidth: Float) {
        mPaint.strokeWidth = mPaintWidth
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlusView, 0,0)
            val color = typedArray.getColor(R.styleable.PlusView_stroke_color, mPaintColor)
            val strokeWidth = typedArray.getDimension(R.styleable.PlusView_stroke_width, mPaintWidth)
            typedArray.recycle()
            mPaint.color = color
            mPaint.strokeWidth = strokeWidth
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize: Int = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val width: Int
        width = if (widthMode == MeasureSpec.EXACTLY) {
            //  MeasureSpec.EXACTLY表示该view设置的确切的数值
            widthSize
        } else {
            60 //默认值
        }
        HendY = width / 2f
        HstartY = HendY
        SsendX = HstartY
        SstartX = SsendX
        HendX = (width - getPadding()).toFloat()
        SsendY = HendX
        HstartX = getPadding().toFloat()
        SstartY = HstartX
        //这样做是因为加号宽高是相等的，手动设置宽高
        setMeasuredDimension(width, width)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //水平的横线
        canvas.drawLine(HstartX, HstartY, HendX, HendY, mPaint)
        //垂直的横线
//        canvas.drawLine(SstartX, SstartY, SsendX, SsendY, mPaint)

    }


}