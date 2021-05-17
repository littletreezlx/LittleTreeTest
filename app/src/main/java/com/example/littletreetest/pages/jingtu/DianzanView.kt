package com.example.littletreetest.pages.jingtu

import android.animation.Animator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp
import timber.log.Timber
import java.util.*
import kotlin.random.Random

class DianzanView(context: Context, attrs: AttributeSet?) : View(context, attrs) {


    init {
        initParams(context, attrs)
    }


    sealed class State {

        //初始未点赞状态
        object UnLiked : State()

        //缩小大拇指
        object ShrinkThumb : State()

        //扩大大拇指，扩散内圈光环直到铺满，开始发射红色拇指背景
        object ExpandThumb : State()

        //扩散外圈光环
        object ExpandRing : State()

        //回弹大拇指
        object ReboundThumb : State()

        //重新扩大大拇指
        object ReExpandThumb : State()

        //重新缩小大拇指，结束发射红色拇指背景
        object ReShrinkThumb : State()

        //长按持续发射红色拇指背景
        object JustSpreadThumb : State()

        //最终点赞状态
        object Liked : State()


        override fun toString(): String {
            return this.javaClass.toString()
        }
    }

    var currentState: State = State.UnLiked


    //Time!!!
    private val shrinkDuration = 480f
    private val shrinkStartTime = 0f

    private val expandThumbDuration = 440f
    private val expandThumbStartTime = shrinkDuration

    private val expandRingDuration = 100f
    private val expandRingStartTime = expandThumbStartTime + expandThumbDuration

    private val reboundThumbDuration = 160f
    private val reboundThumbStartTime = expandRingStartTime + expandRingDuration

    private val reExpandThumbDuration = 80f
    private val reExpandThumbStartTime = reboundThumbStartTime + reboundThumbDuration

    private val reShrinkThumbDuration = 80f
    private val reShrinkThumbStartTime = reExpandThumbStartTime + reExpandThumbDuration

    //    private val wholeDuration = 3500f
    private val wholeDuration = reShrinkThumbStartTime + reShrinkThumbDuration

    private var lastSpreadThumbTime = 0f

    //生成大拇指的时间间隔
    private var randomCreateSpreadThumbSpan = 0f


    //Color!!!
    private val unLikedColor = Color.parseColor("#E8E8E8")

    private val likededColor = Color.parseColor("#F88181")

    private val expandThumbCircleStartColor = Color.parseColor("#E8E8E8")

//    private var expandThumbCurColor = expandThumbStartColor

    private val expandThumbCircleEndColor = Color.parseColor("#F88181")

    private val expandRingColor = Color.parseColor("#F88181")

    //扩散的大拇指有三种随机颜色
    private val spreadThumbColorStr1 = "FB2127"
    private val spreadThumbColorStr2 = "F22C8B"
    private val spreadThumbColorStr3 = "F88181"
    private val spreadThumbColorStrAry =
        arrayOf(spreadThumbColorStr1, spreadThumbColorStr2, spreadThumbColorStr3)


    //只是为了传给Listener
    private var wholeWidth = 0f
    private var wholeHeight = 0f

    private var expandThumbCurCircleR = 0f

    //内圈固定圆半径
    private var inR = 15.dp.toFloat()

    //外圈扩散圆最大半径
    private var outRmax = 18.dp.toFloat()

    //内圈固定圆圆心坐标
    private var centerX = 0f
    private var centerY = 0f

    //初始大拇指的半径，这里不用宽度，用半径比较方便
    private var initThumbR = 9.dp.toFloat()

    //当前大拇指宽度
    private var curThumbWidth = 0f

    //大拇指最小宽度比例
    private var minThumbWidthRadio = 0.87f

    //大拇指最大半径1.3倍
    private var maxThumbWidthRadio = 1.13f

    //第二次扩大时，大拇指最大半径
    private var maxReExpandThumbWidthRadio = 1.01f


    //当前光环半径
    private var curRingR = 0f

    private var initRingStrokeWidth = 2.dp.toFloat()

    private var maxRingStrokeWidth = 4.dp.toFloat()

    private var initCircleStrokeWidth = 1.dp.toFloat()


    //点赞图标
    private val svgDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)

    //当前已经进行动画的时间
    var currentAnimPassedTime = 0f

    private var spreadThumbList = mutableListOf<SpreadThumbPath>()

//    private val svgSpreadDrawable =
//        VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)?.apply {
//            setTint(Color.parseColor("#F98181"))
//        }

    private val unSelectedPaint = Paint().apply {
        strokeWidth = initCircleStrokeWidth
        style = Paint.Style.STROKE
        color = unLikedColor
        isAntiAlias = true
    }

    private val expandThumbInCirclePaint = Paint().apply {
        strokeWidth = 1.dp.toFloat()
        style = Paint.Style.FILL_AND_STROKE
        color = expandThumbCircleStartColor
        isAntiAlias = true
    }

    private val expandRingPaint = Paint().apply {
        strokeWidth = initRingStrokeWidth
        style = Paint.Style.STROKE
        color = expandRingColor
        isAntiAlias = true
    }

    private val likedPaint = Paint().apply {
        strokeWidth = 1.dp.toFloat()
        style = Paint.Style.FILL_AND_STROKE
        color = likededColor
        isAntiAlias = true
    }

    private val wholeAnimator = ValueAnimator.ofFloat(0f, wholeDuration).apply {
        duration = wholeDuration.toLong()
        interpolator = LinearInterpolator()
        addUpdateListener(SingleClickAnimatorListener())
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Timber.d("animation: Start")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Timber.d("animation: Stop")
                if (isOnLongClicked) {
                    spreadThumbColorAnimator.start()
                } else {
                    restoreLiked()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })

    }

    //大拇指扩大时单独计算其颜色
    private val expandThumbColorAnimator =
        ValueAnimator.ofArgb(expandThumbCircleStartColor, expandThumbCircleEndColor).apply {
            duration = expandThumbDuration.toLong()
            interpolator = LinearInterpolator()
            addUpdateListener {
                expandThumbInCirclePaint.color = it.animatedValue as Int
            }
        }


    //长按动画默认关闭时间，
    private val defaultStopTime = 30000f

    var lastTime = 0f

    //长按持续发射大拇指的背景
    private val spreadThumbColorAnimator =
        ValueAnimator.ofFloat(wholeDuration, wholeDuration + defaultStopTime).apply {
            duration = defaultStopTime.toLong()
            interpolator = LinearInterpolator()
            addUpdateListener {
                Timber.d("animatedValue: ${it.animatedValue}")
                currentState = State.JustSpreadThumb
                val passedTime = it.animatedValue as Float
                lastTime = passedTime
                currentAnimPassedTime = passedTime
                calSpreadThumbs(passedTime)
                invalidate()
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    restoreLiked()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    restoreLiked()
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

            })
        }


    //是否在长按状态下
    private var isOnLongClicked = false

    //每隔x秒弹出一个Thumb
//    private val addNewThumbSpan = 30f


    private fun initParams(context: Context, attrs: AttributeSet?) {
        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.RuleCardView, 0, 0)
//            imageRes = typedArray.getResourceId(R.styleable.RuleCardView_rcv_left_image_res, imageRes)
//            typedArray.recycle()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        inR = measuredWidth / 2f - 50.dp
//        outRmax = measuredWidth / 2f - 20.dp
        centerX = measuredWidth / 2f
        centerY = measuredHeight - outRmax
//        initThumbWidth = (measuredWidth / 2f - 70.dp).toInt()
        curThumbWidth = initThumbR
        wholeWidth = measuredWidth.toFloat()
        wholeHeight = measuredHeight.toFloat()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //使坐标原点在canvas中心位置
        canvas.translate(centerX, centerY)
        Timber.d("state: ${currentState}")
        when (currentState) {
            State.UnLiked -> {
                drawOriginView(canvas)
            }
            State.ShrinkThumb -> {
                drawShrinkThumb(canvas)
            }
            State.ExpandThumb -> {
                drawSpreadThumbs(canvas)
                drawExpandThumb(canvas)
            }
            State.ExpandRing -> {
                drawSpreadThumbs(canvas)
                drawExpandRing(canvas)
            }
            State.ReboundThumb -> {
                drawSpreadThumbs(canvas)
                drawReboundThumb(canvas)
            }
            State.ReExpandThumb -> {
                drawSpreadThumbs(canvas)
                drawFixedSelectedView(canvas)
            }
            State.ReShrinkThumb -> {
                drawSpreadThumbs(canvas)
                drawFixedSelectedView(canvas)
            }
            State.JustSpreadThumb -> {
                drawSpreadThumbs(canvas)
                drawFixedSelectedView(canvas)
            }
            State.Liked -> {
                drawLikedView(canvas)
            }
        }
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
                -initThumbR.toInt(),
                -initThumbR.toInt(),
                initThumbR.toInt(),
                initThumbR.toInt()
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


    private fun drawSpreadThumbs(canvas: Canvas) {
        spreadThumbList.forEach {
            if (!it.isAvailable) {
                return@forEach
            }
            it.drawable.setBounds(
                it.curX - it.radius,
                it.curY - it.radius,
                it.curX + it.radius,
                it.curY + it.radius,
            )
            it.drawable.draw(canvas)
        }
    }


    private fun drawLikedView(canvas: Canvas) {
        canvas.drawCircle(0f, 0f, inR, likedPaint)
        svgDrawable?.let {
            it.setBounds(
                -initThumbR.toInt(),
                -initThumbR.toInt(),
                initThumbR.toInt(),
                initThumbR.toInt()
            )
            it.draw(canvas)
        }
    }

    //复用已点赞的圆图标
    private fun drawFixedSelectedView(canvas: Canvas) {
        canvas.drawCircle(0f, 0f, expandThumbCurCircleR, expandThumbInCirclePaint)
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


    private fun initSpreadThumbs() {
        spreadThumbList.clear()
//        spreadThumbList.addAll(MutableList(20) {
//            getRandomSpreadThumb()
//        })
    }


    private fun calSpreadThumbs(animatedValue: Float) {
        if (animatedValue - lastSpreadThumbTime > randomCreateSpreadThumbSpan) {
            spreadThumbList.add(getRandomSpreadThumb(animatedValue))
            lastSpreadThumbTime = animatedValue
            randomCreateSpreadThumbSpan = 25f + Random.nextInt(50)
            Timber.d("LLL: ${spreadThumbList.size}")
        }
        spreadThumbList.forEach {
            //到顶了就不画了
            if (!it.isAvailable) {
                return@forEach
            }
            if (animatedValue - it.startAnimVal > it.duration) {
                it.isAvailable = false
                return@forEach
            }
            val percent = (animatedValue - it.startAnimVal) / it.duration
            it.curX = it.startX + ((it.endX - it.startX) * percent).toInt()
            it.curY = it.startY + ((it.endY - it.startY) * percent).toInt()
        }
    }

//
//    private fun addSpreadThumb(startAnimVal:Float = 0f) {
//        spreadThumbList.add(getRandomSpreadThumb(startAnimVal))
//    }


    private fun getRandomSpreadThumb(startAnimVal: Float = 0f) = SpreadThumbPath(
        startX = 0,
        startY = 0,
        endX = Random.nextInt(wholeWidth.toInt()) - (wholeWidth / 2).toInt(),
        endY = (-wholeHeight + outRmax + 20.dp + Random.nextInt(50).dp).toInt(),
        curX = 0,
        curY = 0,
        radius = 5.dp + Random.nextInt(4),
        duration = 450f + Random.nextInt(100),
        startAnimVal = startAnimVal,
//        color = getRandomSpreadColor(),
        drawable = VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)!!.apply {
            setTint(getRandomSpreadColor())
        }
    )


    fun getRandomSpreadColor(): Int {
        val baseColor = spreadThumbColorStrAry[Random.nextInt(3)]
//        val aplphaStr = Random.nextInt(256).toString(16)
        var alphaStr = Random.nextInt(243).toString(16)
        if (alphaStr.length == 1) {
            alphaStr = "0$alphaStr"
        }
        val colorStr = "#$alphaStr$baseColor"
//        Timber.d("TTT$colorStr")
        return Color.parseColor(colorStr)
    }


    inner class SingleClickAnimatorListener : AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val animatedValue = animation.animatedValue as Float
            currentAnimPassedTime = animatedValue
            Timber.d("animatedValue:$animatedValue")
            when (animatedValue) {
                0f -> {
                    currentState = State.UnLiked
//                    if (!addNewThumbAnimator.isRunning) {
//                        addNewThumbAnimator.start()
//                    }
                }
                in 0f..shrinkDuration -> {
                    currentState = State.ShrinkThumb
                    //最小半径0.8倍
                    val percent: Float = animatedValue / shrinkDuration
                    Timber.d("percent:$percent")
                    curThumbWidth =
                        initThumbR - (1 - minThumbWidthRadio) * initThumbR * percent
                }
//                expandThumbStartTime -> {
//
//                }
                in expandThumbStartTime..expandRingStartTime -> {
                    currentState = State.ExpandThumb
                    if (!expandThumbColorAnimator.isRunning) {
                        expandThumbColorAnimator.start()
                    }
                    val percent = (animatedValue - expandThumbStartTime) / expandThumbDuration
                    curThumbWidth =
                        initThumbR * minThumbWidthRadio + (maxThumbWidthRadio - minThumbWidthRadio) * initThumbR * percent
                    expandThumbCurCircleR = inR * percent
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    calSpreadThumbs(animatedValue)
                }

                in expandRingStartTime..reboundThumbStartTime -> {
                    currentState = State.ExpandRing
                    val percent: Float = (animatedValue - expandRingStartTime) / expandRingDuration
                    Timber.d("percent:$percent")
                    curRingR = inR + (outRmax - inR) * percent
                    expandRingPaint.strokeWidth =
                        initRingStrokeWidth + (maxRingStrokeWidth - initRingStrokeWidth) * percent
                    Timber.d("strokeWidth:${expandRingPaint.strokeWidth}")
                    calSpreadThumbs(animatedValue)
                }

                in reboundThumbStartTime..reboundThumbStartTime + reboundThumbDuration -> {
                    currentState = State.ReboundThumb
                    val percent: Float =
                        (animatedValue - reboundThumbStartTime) / reboundThumbDuration
                    curThumbWidth =
                        initThumbR * maxThumbWidthRadio - (maxThumbWidthRadio - 1) * initThumbR * percent
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    calSpreadThumbs(animatedValue)
                }

                in reExpandThumbStartTime..reExpandThumbStartTime + reExpandThumbDuration -> {
                    currentState = State.ReExpandThumb
                    val percent: Float =
                        (animatedValue - reExpandThumbStartTime) / reExpandThumbDuration
                    curThumbWidth =
                        initThumbR * maxThumbWidthRadio + (maxReExpandThumbWidthRadio - 1) * initThumbR * percent
                    calSpreadThumbs(animatedValue)
                }

                // -1是为了留like的状态
                in reShrinkThumbStartTime..reShrinkThumbStartTime + reShrinkThumbDuration -> {
                    currentState = State.ReExpandThumb
                    val percent: Float =
                        (animatedValue - reShrinkThumbStartTime) / reShrinkThumbDuration
                    curThumbWidth =
                        initThumbR * maxReExpandThumbWidthRadio - (maxReExpandThumbWidthRadio - 1) * initThumbR * percent
                    calSpreadThumbs(animatedValue)
                }

//                else -> {
//                    restoreLiked()
//                }
            }
            invalidate()
        }
    }


//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        return super.onTouchEvent(event)
//
//        when(event.action){
//            MotionEvent.ACTION_DOWN
//        }
//
//
//        Timber.d("touch:${event.action}")
//    }


    private fun restoreData() {
        curThumbWidth = initThumbR
        initSpreadThumbs()
        currentAnimPassedTime = 0f
        lastSpreadThumbTime = 0f
    }


    fun restoreUnLiked() {
//        restoreData()
        currentState = State.UnLiked
        invalidate()
    }


    fun restoreLiked() {
//        restoreData()
        currentState = State.Liked
        invalidate()
    }


    fun startClickAnim() {
        restoreData()
        wholeAnimator.start()
    }


    private fun stopLongClickAnim() {
        wholeAnimator.cancel()
        spreadThumbColorAnimator.cancel()
        restoreLiked()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                when (currentState) {
                    State.UnLiked -> {
                        onSelectedFun.invoke()
                        isOnLongClicked = true
                        startClickAnim()
                    }
                    State.Liked -> {
                        onUnSelectedFun.invoke()
                        restoreData()
                        restoreUnLiked()
                    }
                    //不允许点击中间状态
                    else -> {
                        return true
                    }
                }
                Timber.d("touch: down")
            }
            MotionEvent.ACTION_CANCEL -> {
                Timber.d("touch: cancel")
            }
            MotionEvent.ACTION_UP -> {
                isOnLongClicked = false
                Timber.d("touch: up")
                Timber.d("touch: $currentAnimPassedTime")
                Timber.d("touch: $wholeDuration")
                if (currentAnimPassedTime < wholeDuration) {
                    return true
                }
                stopLongClickAnim()
            }
        }
        return true
    }


    var onSelectedFun: () -> Unit = {}

    var onUnSelectedFun: () -> Unit = {}


    data class SpreadThumbPath(
        val startX: Int = 0,
        val startY: Int = 0,
        val endX: Int = 0,
        val endY: Int = 0,
        var curX: Int = 0,
        var curY: Int = 0,
        var radius: Int = 5.dp,
        //or speed
        //飞行的持续时间
        var duration: Float = 450f,
        //启动时，动画的进度
        var startAnimVal: Float = 0f,

//        var color: Int = Color.parseColor("#FB2127"),

        var alpha: Boolean = true,

        var drawable: VectorDrawableCompat,
        //是否可用，约等于超时到顶
        var isAvailable: Boolean = true,


        ) {
        companion object {
//            fun getRandomPath() =
        }
    }


}

