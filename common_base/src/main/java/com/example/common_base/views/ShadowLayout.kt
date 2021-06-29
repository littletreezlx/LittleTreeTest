package com.example.common_base.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.jingtu.R
import timber.log.Timber

class ShadowLayout : FrameLayout {
    /**
     * 阴影颜色
     */
    private var mShadowColor = 0


    private var fillColor = 0


    private var forceUpdatedColor = 0


    /**
     * 阴影的扩散范围(也可以理解为扩散程度)
     */
    private var mShadowBlur = 0f

    /**
     * 阴影的圆角大小
     */
    private var mCornerRadius = 0f

    /**
     * x轴的偏移量
     */
    private var mDx = 0f

    /**
     * y轴的偏移量
     */
    private var mDy = 0f

    /**
     * 左边是否显示阴影
     */
    private var leftShow = false

    /**
     * 右边是否显示阴影
     */
    private var rightShow = false

    /**
     * 上边是否显示阴影
     */
    private var topShow = false

    /**
     * 下面是否显示阴影
     */
    private var bottomShow = false


    private var mInvalidateShadowOnSizeChanged = true
    private var mForceInvalidateShadow = false


    private var xPadding = 0

    private var yPadding = 0

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    override fun getSuggestedMinimumWidth(): Int {
        return 0
    }

    override fun getSuggestedMinimumHeight(): Int {
        return 0
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0 && (background == null || mInvalidateShadowOnSizeChanged
                    || mForceInvalidateShadow)
        ) {
            mForceInvalidateShadow = false
            setBackgroundCompat(w, h)
        }
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false
            setBackgroundCompat(right - left, bottom - top)
        }
    }

    fun setInvalidateShadowOnSizeChanged(invalidateShadowOnSizeChanged: Boolean) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged
    }

    fun invalidateShadow() {
        mForceInvalidateShadow = true
        requestLayout()
        invalidate()
    }


    private fun initView(
        context: Context,
        attrs: AttributeSet?
    ) {
        initAttributes(context, attrs)
        xPadding = (mShadowBlur + Math.abs(mDx)).toInt()
        yPadding = (mShadowBlur + Math.abs(mDy)).toInt()
        val left: Int = if (leftShow) {
            xPadding
        } else {
            0
        }
        val top: Int = if (topShow) {
            yPadding
        } else {
            0
        }
        val right: Int = if (rightShow) {
            xPadding
        } else {
            0
        }
        val bottom: Int = if (bottomShow) {
            yPadding
        } else {
            0
        }
        setPadding(left, top, right, bottom)
    }


    private fun setBackgroundCompat(w: Int, h: Int) {
        val bitmap = createShadowBitmap(
            w,
            h,
            mCornerRadius,
            mShadowBlur,
            mDx,
            mDy,
            mShadowColor,
            fillColor
        )
//        Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowLimit, mDx, mDy, mShadowColor, Color.TRANSPARENT);
        val drawable = BitmapDrawable(resources, bitmap)
        background = drawable
    }

    private fun initAttributes(
        context: Context,
        attrs: AttributeSet?
    ) {
        val attr =
            getTypedArray(context, attrs, R.styleable.ShadowFrameLayout)
                ?: return
        try {
            //默认是显示
//            leftShow = attr.getBoolean(R.styleable.ShadowLayout_yc_leftShow, true);
//            rightShow = attr.getBoolean(R.styleable.ShadowLayout_yc_rightShow, true);
//            bottomShow = attr.getBoolean(R.styleable.ShadowLayout_yc_bottomShow, true);
//            topShow = attr.getBoolean(R.styleable.ShadowLayout_yc_topShow, true);
            leftShow = true
            rightShow = true
            bottomShow = true
            topShow = true
            mCornerRadius = attr.getDimension(R.styleable.ShadowFrameLayout_shadow_radius, 0f)
            mShadowBlur = attr.getDimension(R.styleable.ShadowFrameLayout_shadow_blur, 0f)
            mDx = attr.getDimension(R.styleable.ShadowFrameLayout_shadow_dx, 0f)
            mDy = attr.getDimension(R.styleable.ShadowFrameLayout_shadow_dy, 0f)
            mShadowColor = attr.getColor(
                R.styleable.ShadowFrameLayout_shadow_color,
                Color.argb(90, 0, 0, 0)
            )

            if (forceUpdatedColor == 0) {
                fillColor = attr.getColor(
                    R.styleable.ShadowFrameLayout_fill_color,
                    Color.parseColor("#FFFFFF")
                )
            } else {
                fillColor = forceUpdatedColor
            }
        } finally {
            attr.recycle()
        }
    }

    private fun getTypedArray(
        context: Context,
        attributeSet: AttributeSet?,
        attr: IntArray
    ): TypedArray {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0)
    }

    private fun createShadowBitmap(
        shadowWidth: Int, shadowHeight: Int, cornerRadius: Float,
        shadowRadius: Float, dx: Float, dy: Float,
        shadowColor: Int, fillColor: Int
    ): Bitmap {

        //根据宽高创建bitmap背景
        val output = Bitmap.createBitmap(
            shadowWidth,
            shadowHeight,
            Bitmap.Config.ARGB_8888
        )
        //用画板canvas进行绘制
        val canvas = Canvas(output)
        val shadowRect = RectF(
            shadowRadius, shadowRadius,
            shadowWidth - shadowRadius, shadowHeight - shadowRadius
        )
        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy)
            shadowRect.bottom -= Math.abs(dy)
        }
        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx)
            shadowRect.right -= Math.abs(dx)
        }
        val shadowPaint = Paint()
        shadowPaint.isAntiAlias = true
        shadowPaint.color = fillColor
        shadowPaint.style = Paint.Style.FILL
        if (!isInEditMode) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)
        return output
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        Timber.d("X:${event.x}. Y:${event.y}")
        Timber.d("${xPadding},${width - xPadding}, ${xPadding}, ${height - xPadding}")
        if (event.x > xPadding
            && event.x < width - xPadding
            && event.y > xPadding
            && event.y < height - xPadding
        ) {
            return super.onTouchEvent(event)
        } else {
            //在实际内容范围外，包括点击到阴影！
            return true
        }
    }


    fun updateBackgroundColor(color: Int) {
        fillColor = color
        invalidateShadow()
    }
}