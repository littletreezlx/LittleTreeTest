package com.example.littletreetest.pages.jingtu

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp
import timber.log.Timber

class DianzanView(context: Context, attrs: AttributeSet?) : View(context, attrs) {


    init {
        initParams(context, attrs)
//        initViews(context)
    }


    sealed class State {

        //初始未点赞状态
        object Origin : State()

        //缩小大拇指
        object ShrinkThumb : State()

        //缩小大拇指，同时扩散内圈光环
        object ExpandThumb : State()

        //扩散外圈光环
        object ExpandRing : State()

        //回弹大拇指
        object ReboundThumb : State()

    }

    var currentState: State = State.Origin

    private val shrinkDuration = 1000f
    private val expandThumbDuration = 1000f
    private val expandThumbStartTime = shrinkDuration
    private val expandRingDuration = 1000f
    private val expandRingStartTime = shrinkDuration + expandThumbDuration
    private val reboundThumbDuration = 500f
    private val reboundThumbStartTime = shrinkDuration + expandThumbDuration + expandRingDuration

    //    private val wholeDuration = 3500f
    private val wholeDuration =
        shrinkDuration + expandThumbDuration + expandRingDuration + reboundThumbStartTime


    //    private val unSelectedColor = Color.parseColor("#E8E8E8")
    private val unSelectedColor = Color.parseColor("#FFFFFF")

    private val expandThumbStartColor = Color.parseColor("#99FFFFFF")

//    private var expandThumbCurColor = expandThumbStartColor

    private val expandThumbEndColor = Color.parseColor("#F98181")

    private var expandThumbCurCircleR = 0f


    //内圈固定圆半径
    private var inR = 0f

    //外圈扩散圆最大半径
    private var outRmax = 0f

    //内圈固定圆圆心坐标
    private var centerX = 0f
    private var centerY = 0f

    //初始大拇指宽度
    private var initThumbWidth = 30.dp.toFloat()

    //当前大拇指宽度
    private var curThumbWidth = 0f

    //当前大拇指宽度
    private var minThumbWidthRadio = 0.7f

    //最大半径1.2倍
    //这边应该是按最小半径为除数来算倍数
    private var maxThumbWidthRadio = 1.3f

    //当前光环半径
    private var curRingR = 0f

    private var initRingStrokeWidth = 5.dp.toFloat()

    //    private var curRingStrokeWidth = initRingStrokeWidth
    //点赞图标
    private val svgDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)


    private val unSelectedPaint = Paint().apply {
        strokeWidth = 5.dp.toFloat()
        style = Paint.Style.STROKE
        color = Color.parseColor("#FFFFFF")
        isAntiAlias = true
    }

    private val expandThumbPaint = Paint().apply {
        strokeWidth = 5.dp.toFloat()
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#99FFFFFF")
        isAntiAlias = true
    }

    private val expandRingPaint = Paint().apply {
        strokeWidth = initRingStrokeWidth
        style = Paint.Style.STROKE
        color = Color.parseColor("#F18A8A")
        isAntiAlias = true
    }


    private val animatorListener = LvAnimatorListener()

    private val wholeAnimator = ValueAnimator.ofFloat(0f, wholeDuration).apply {
        duration = wholeDuration.toLong()
//        interpolator = LinearInterpolator()
        addUpdateListener(animatorListener)
    }

    private val expandThumbColorAnimator =
        ValueAnimator.ofArgb(expandThumbStartColor, expandThumbEndColor).apply {
            duration = expandThumbDuration.toLong()
//        interpolator = LinearInterpolator()
            addUpdateListener {
//                expandThumbCurColor = it as Int
                Timber.d("color:${it}")
                expandThumbPaint.color = it.animatedValue as Int
            }
        }


    private fun initParams(context: Context, attrs: AttributeSet?) {
        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.RuleCardView, 0, 0)
//            imageRes = typedArray.getResourceId(R.styleable.RuleCardView_rcv_left_image_res, imageRes)
//            typedArray.recycle()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        inR = measuredWidth / 2f - 50.dp
        outRmax = measuredWidth / 2f - 20.dp
        centerX = measuredWidth / 2f
        centerY = measuredHeight - outRmax
//        initThumbWidth = (measuredWidth / 2f - 70.dp).toInt()
        curThumbWidth = initThumbWidth
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //使坐标原点在canvas中心位置
        canvas.translate(centerX, centerY)
        Timber.d(currentState.toString())
        when (currentState) {
            State.Origin -> {
                drawOriginView(canvas)
            }
            State.ShrinkThumb -> {
                drawShrinkThumb(canvas)
            }
            State.ExpandThumb -> {
                drawExpandThumb(canvas)
            }
            State.ExpandRing -> {
                drawExpandRing(canvas)
            }
            State.ReboundThumb -> {
                drawReboundThumb(canvas)
            }
        }
    }


    private fun drawRestore() {
        currentState = State.Origin
        invalidate()
    }


    private fun drawOriginView(canvas: Canvas) {
//        inR = width / 2f - 50.dp
//        outRmax = width / 2f - 20.dp
//        centerX = width / 2f
//        centerY = height - outRmax
//        thumbWidth = (width / 2f - 60.dp).toInt()
        canvas.drawCircle(0f, 0f, inR, unSelectedPaint)
        svgDrawable?.let {
            it.setBounds(
                -initThumbWidth.toInt(),
                -initThumbWidth.toInt(),
                initThumbWidth.toInt(),
                initThumbWidth.toInt()
            )
            it.draw(canvas)
        }
    }


    private fun drawShrinkThumb(canvas: Canvas) {
        canvas.drawCircle(0f, 0f, inR, unSelectedPaint)
        svgDrawable?.let {
            it.setBounds(
                -curThumbWidth.toInt(),
                -curThumbWidth.toInt(),
                curThumbWidth.toInt(),
                curThumbWidth.toInt()
            )
            it.draw(canvas)
        }
    }

    private fun drawExpandThumb(canvas: Canvas) {
        canvas.drawCircle(0f, 0f, inR, unSelectedPaint)
        drawFixedSelectedView(canvas)
//        canvas.drawCircle(0f, 0f, expandThumbCurCircleR, expandThumbPaint)
//        svgDrawable?.let {
//            it.setBounds(-curThumbWidth.toInt(), -curThumbWidth.toInt(), curThumbWidth.toInt(), curThumbWidth.toInt())
//            it.draw(canvas)
//        }

    }


    //这里是不是可以保存上一段的场景
    private fun drawExpandRing(canvas: Canvas) {
        drawFixedSelectedView(canvas)
        if (expandRingPaint.strokeWidth != 0f) {
            canvas.drawCircle(0f, 0f, curRingR, expandRingPaint)
        }
    }


    private fun drawReboundThumb(canvas: Canvas) {
        drawFixedSelectedView(canvas)
    }


    //复用已点赞的圆图标
    private fun drawFixedSelectedView(canvas: Canvas) {
        canvas.drawCircle(0f, 0f, expandThumbCurCircleR, expandThumbPaint)
        svgDrawable?.let {
            it.setBounds(
                -curThumbWidth.toInt(),
                -curThumbWidth.toInt(),
                curThumbWidth.toInt(),
                curThumbWidth.toInt()
            )
            it.draw(canvas)
        }
    }


    fun startSelectViewMotion() {
//        resetState()
        wholeAnimator.start()
    }


    inner class LvAnimatorListener : AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val animatedValue = animation.animatedValue as Float
            Timber.d("animatedValue:$animatedValue")
            when (animatedValue) {
                0f -> {
                    currentState = State.Origin
                }
                in 0f..shrinkDuration -> {
                    //最小半径0.8倍
                    val percent: Float = animatedValue / shrinkDuration
                    Timber.d("percent:$percent")
                    curThumbWidth = initThumbWidth - (1 - minThumbWidthRadio) * initThumbWidth * percent
                    currentState = State.ShrinkThumb
                    invalidate()
                }
                expandThumbStartTime -> {

                }
                in expandThumbStartTime..expandThumbStartTime + expandThumbDuration -> {
                    currentState = State.ExpandThumb
                    if (!expandThumbColorAnimator.isRunning) {
                        expandThumbColorAnimator.start()
                    }
                    val percent = (animatedValue - expandThumbStartTime) / shrinkDuration
                    Timber.d("percent:$percent")
                    curThumbWidth =
                        initThumbWidth * minThumbWidthRadio + (maxThumbWidthRadio - minThumbWidthRadio) * initThumbWidth * percent
                    expandThumbCurCircleR =
                        inR * (animatedValue - expandThumbStartTime) / expandThumbDuration
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    invalidate()
                }

                in expandRingStartTime..expandRingStartTime + expandRingDuration -> {
                    currentState = State.ExpandRing
                    val percent: Float = (animatedValue - expandRingStartTime) / expandRingDuration
                    Timber.d("percent:$percent")
                    curRingR = inR * (1 + percent * 0.2f)
                    expandRingPaint.strokeWidth = initRingStrokeWidth * (1 - percent)
                    Timber.d("strokeWidth:${expandRingPaint.strokeWidth}")
                    invalidate()
                }
                in reboundThumbStartTime..reboundThumbStartTime + reboundThumbDuration -> {
                    currentState = State.ReboundThumb
                    val percent: Float =
                        (animatedValue - reboundThumbStartTime) / reboundThumbDuration
                    curThumbWidth =
                        initThumbWidth * maxThumbWidthRadio - (maxThumbWidthRadio - 1) * initThumbWidth * percent
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    invalidate()
                }

                else -> {
                    curThumbWidth = initThumbWidth
                }

            }
        }
    }

//    inner class AnimatorListener : ObjectAnimator() {
//
//    }

    fun retore() {
        drawRestore()
    }


}

