package com.example.littletreetest.pages.ui.popupwindow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp2px
import timber.log.Timber
import kotlin.math.cosh

class AnglePopupLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    companion object {
        const val ANGLE_ORIENTATION_DOWN = 0

        const val ANGLE_ORIENTATION_UP = 1
    }


    // 布局整体高度
    var mHeight: Float = 100f


    //布局整体宽度
    var mWidth: Float = 100f


    //圆角
//    var mRadius: Float = dp2px(10f).toFloat()
    var mRadius: Float = 0f


    //背景颜色
    var mBgColor: Int = Color.WHITE


    //边框线宽度
    var mStrokeWidth: Float = dp2px(1f).toFloat()

    //边框线颜色
    var mStrokeColor: Int = Color.parseColor("#333333")


    //三角朝向
    var angleOrientation: Int = ANGLE_ORIENTATION_DOWN

    //三角的x轴位置
    var angleX: Float = dp2px(18f).toFloat()

    //三角高度
    var angleHeight: Float = dp2px(6f).toFloat()
//    val angleHeight: Float = dp2px(30f).toFloat()

    //三角宽度
    var angleWidth: Float = dp2px(10f).toFloat()
//    val angleWidth: Float = dp2px(30f).toFloat()


    //阴影扩散长度
//    var mShadowBlur: Float = dp2px(20f).toFloat()
    var mShadowBlur: Float = 0f


    //x轴的偏移量
    var mDx = 0f

    // y轴的偏移量
    var mDy = 0f

    //阴影颜色
//    var mShadowColor: Int = Color.parseColor("#333333")

    //阴影颜色
    var mShadowColor: Int = Color.BLACK

    var strokeRect = RectF()

    var fillRect = RectF()

    var paint = Paint()

    var angleStrokePath = Path()

    var angleFillPath = Path()


    init {
        setWillNotDraw(false)
        initAttributes(context, attrs)
    }


    private fun initAttributes(
        context: Context,
        attrs: AttributeSet?
    ) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AnglePopupLayout, 0, 0)
        attr.run {
            mRadius = getDimension(R.styleable.AnglePopupLayout_apl_radius, mRadius)
            mBgColor = getColor(R.styleable.AnglePopupLayout_apl_bg_color, mBgColor)

            mStrokeWidth = getDimension(R.styleable.AnglePopupLayout_apl_stroke_width, mStrokeWidth)
            mStrokeColor = getColor(R.styleable.AnglePopupLayout_apl_stroke_color, mStrokeColor)

            angleOrientation =
                getInt(R.styleable.AnglePopupLayout_apl_angle_orientation, angleOrientation)
            angleX = getDimension(R.styleable.AnglePopupLayout_apl_angle_x, angleX)
            angleHeight = getDimension(R.styleable.AnglePopupLayout_apl_angle_height, angleHeight)
            angleWidth = getDimension(R.styleable.AnglePopupLayout_apl_angle_width, angleWidth)

            mShadowBlur = getDimension(R.styleable.AnglePopupLayout_apl_shadow_blur, mShadowBlur)
            mDx = getDimension(R.styleable.AnglePopupLayout_apl_shadow_dx, mDx)
            mDy = getDimension(R.styleable.AnglePopupLayout_apl_shadow_dy, mDy)
            mShadowColor = getColor(R.styleable.AnglePopupLayout_apl_shadow_color, mShadowColor)

            recycle()
        }
        //        try {
//        } finally {
//            attr.recycle()
//        }

        //处理数据
        val blur = mShadowBlur.toInt()
        var topPadding = blur
        var bottomPadding = blur
        if (angleOrientation == ANGLE_ORIENTATION_UP) {
            topPadding += angleHeight.toInt()
        } else {
            bottomPadding += angleHeight.toInt()
        }
        setPadding(blur, topPadding, blur, bottomPadding)

    }


//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }


    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = (right - left).toFloat()
        mHeight = (bottom - top).toFloat()

        Timber.d("height: ${dp2px(50f)}")
        Timber.d("height: ${mHeight}")
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //边框+ 阴影
        paint.run {
            strokeWidth = mStrokeWidth
            color = mStrokeColor
            style = Paint.Style.STROKE
            isAntiAlias = true
            if (mShadowBlur == 0f) {

            } else {
                setShadowLayer(mShadowBlur, mDx, mDy, mShadowColor)
            }
            //for test
//            setBackgroundColor(Color.parseColor("#70707070"))
        }

        val halfStrokeWidth = mStrokeWidth / 2
        val adjust = halfStrokeWidth + mShadowBlur
        var adjustedTop = adjust
        var adjustedBottom = mHeight - adjust
        if (angleOrientation == ANGLE_ORIENTATION_UP) {
            adjustedTop += angleHeight
        } else {
            adjustedBottom -= angleHeight
        }
        strokeRect.run {
            left = adjust
            top = adjustedTop
            right = mWidth - adjust
            bottom = adjustedBottom
        }
        fillRect.run {
            left = adjust + halfStrokeWidth
            top = adjustedTop + halfStrokeWidth
            right = mWidth - adjust - halfStrokeWidth
            bottom = adjustedBottom - halfStrokeWidth
        }

        //三角
        var angleStartY = 0f
        var angleMoveDy = 0f
        var angleFillStartY = 0f
        var angleFillMoveDy = 0f
        if (angleOrientation == ANGLE_ORIENTATION_UP) {
            angleStartY = adjustedTop
            angleMoveDy = -angleHeight
            angleFillStartY = angleStartY + halfStrokeWidth
            angleFillMoveDy = -angleHeight + halfStrokeWidth
        } else {
            angleStartY = adjustedBottom
            angleMoveDy = angleHeight
            angleFillStartY = angleStartY - halfStrokeWidth
            angleFillMoveDy = angleHeight - halfStrokeWidth
        }
        //三角边框
        angleStrokePath.run {
            moveTo(angleX + mShadowBlur, angleStartY)
            rLineTo(angleWidth / 2, angleMoveDy)
            rLineTo(angleWidth / 2, -angleMoveDy)
        }
        //三角填充
        angleFillPath.run {
            moveTo(angleX + mShadowBlur + halfStrokeWidth, angleFillStartY)
            rLineTo(angleWidth / 2 - halfStrokeWidth, angleFillMoveDy)
            rLineTo(angleWidth / 2 - halfStrokeWidth, -angleFillMoveDy)
            close()
        }
        //矩形边框
        canvas.drawRoundRect(
            strokeRect,
            mRadius,
            mRadius,
            paint
        )
        //三角边框
        canvas.drawPath(angleStrokePath, paint)
        //消三角区域边框线
        //三角填充
        paint.run {
            color = Color.WHITE
            strokeWidth = 0f
            style = Paint.Style.FILL
            clearShadowLayer()
        }
        canvas.drawPath(angleFillPath, paint)

        //矩形填充
        canvas.drawRoundRect(
            fillRect,
            mRadius,
            mRadius,
            paint
        )
    }


    override fun invalidate() {
        angleStrokePath = Path()
        angleFillPath = Path()
        super.invalidate()

    }
}