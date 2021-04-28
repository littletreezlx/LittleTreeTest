package com.example.littletreetest.pages.jingtu

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp
import timber.log.Timber
import java.time.Duration
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

        //回弹大拇指，结束发射红色拇指背景
        object ReboundThumb : State()

        //最终点赞状态
        object Liked : State()

    }

    var currentState: State = State.UnLiked

    private val shrinkDuration = 300f
    private val expandThumbDuration = 800f
    private val expandThumbStartTime = shrinkDuration
    private val expandRingDuration = 500f
    private val expandRingStartTime = shrinkDuration + expandThumbDuration
    private val reboundThumbDuration = 200f
    private val reboundThumbStartTime = shrinkDuration + expandThumbDuration + expandRingDuration
    //    private val wholeDuration = 3500f
    private val wholeDuration =
        shrinkDuration + expandThumbDuration + expandRingDuration + reboundThumbDuration

    //    private val unSelectedColor = Color.parseColor("#E8E8E8")
    private val unSelectedColor = Color.parseColor("#FFFFFF")

    private val expandThumbStartColor = Color.parseColor("#99FFFFFF")

//    private var expandThumbCurColor = expandThumbStartColor

    private val expandThumbEndColor = Color.parseColor("#F98181")

    //只是为了传给Listener
    private var wholeWidth = 0f
    private var wholeHeight = 0f

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

    //大拇指最小宽度比例
    private var minThumbWidthRadio = 0.7f

    //大拇指最大半径1.3倍
    private var maxThumbWidthRadio = 1.3f

    //当前光环半径
    private var curRingR = 0f

    private var initRingStrokeWidth = 5.dp.toFloat()

    //    private var curRingStrokeWidth = initRingStrokeWidth
    //点赞图标
    private val svgDrawable = VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)

//    private val svgSpreadDrawable =
//        VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)?.apply {
//            setTint(Color.parseColor("#F98181"))
//        }

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
            addUpdateListener {
                expandThumbPaint.color = it.animatedValue as Int
            }
        }

    //每隔x秒弹出一个Thumb
    private val addNewThumbSpan = 30f

//    private val addNewThumbAnimator =
//        ValueAnimator.ofInt(0, (wholeDuration / addNewThumbSpan).toInt()).apply {
//            duration = wholeDuration.toLong()
//            addUpdateListener {
//                Timber.d("addNew:${(it.animatedValue as Int)}")
//                addSpreadThumb((it.animatedValue as Int) * addNewThumbSpan)
//            }
//        }


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


        wholeWidth = measuredWidth.toFloat()
        wholeHeight = measuredHeight.toFloat()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Timber.d("Thread_draw:${Thread.currentThread().name}")
        //使坐标原点在canvas中心位置
        canvas.translate(centerX, centerY)
        Timber.d(currentState.toString())
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
                drawReboundThumb(canvas)
            }
            State.Liked -> {
                drawFixedSelectedView(canvas)
            }
        }
    }


    private fun drawRestore() {
        currentState = State.UnLiked
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


    private fun drawSpreadThumbs(canvas: Canvas) {
        spreadThumbList.forEach {
            if (!it.isAvailable) {
                return@forEach
            }
            val drawable =
                VectorDrawableCompat.create(resources, R.drawable.ic_dianzan, null)?.apply {
                    setTint(Color.parseColor("#FE4144"))
                }
            drawable?.setBounds(
                (it.curX - it.radius).toInt(),
                (it.curY - it.radius).toInt(),
                (it.curX + it.radius).toInt(),
                (it.curY + it.radius).toInt(),
            )
            drawable?.draw(canvas)
        }
    }


    data class SpreadThumbPath(
        val startX: Float = 0f,
        val startY: Float = 0f,
        val endX: Float = 0f,
        val endY: Float = 0f,
        var curX: Float = 0f,
        var curY: Float = 0f,
        var radius: Float = 10.dp.toFloat(),
        //or speed
        //飞行的持续时间
        var duration: Float = 500f,
        //启动时，动画的进度
        var startAnimVal: Float = 0f,
        //是否可用，约等于超时到顶
        var isAvailable: Boolean = true,
    ) {
        companion object {
//            fun getRandomPath() =
        }
    }

    var spreadThumbList = mutableListOf<SpreadThumbPath>()

//    private fun s(){
//
//    }

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


    private fun initSpreadThumbs() {
        spreadThumbList.clear()
        spreadThumbList.addAll(MutableList(20) {
            getRandomSpreadThumb()
        })
    }


    private var addedNewThumNum = 0

    private fun calSpreadThumbs(animatedValue: Float) {
        if (animatedValue / addNewThumbSpan > addedNewThumNum) {
            addedNewThumNum++
            spreadThumbList.add(getRandomSpreadThumb(animatedValue))
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
            it.curX = it.startX + (it.endX - it.startX) * percent
            it.curY = it.startY + (it.endY - it.startY) * percent
        }
    }

//
//    private fun addSpreadThumb(startAnimVal:Float = 0f) {
//        spreadThumbList.add(getRandomSpreadThumb(startAnimVal))
//    }


    private fun getRandomSpreadThumb(startAnimVal: Float = 0f) = SpreadThumbPath(
        startX = 0f,
        startY = 0f,
        endX = Random.nextInt(wholeWidth.toInt()) - wholeWidth / 2,
        endY = -wholeHeight + outRmax + 20.dp + Random.nextInt(50).dp,
        curX = 0f,
        curY = 0f,
        radius = 15.dp.toFloat() + Random.nextInt(5),
        duration = 500f + Random.nextInt(30),
        startAnimVal = startAnimVal,
    )


    inner class LvAnimatorListener : AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val animatedValue = animation.animatedValue as Float
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
                        initThumbWidth - (1 - minThumbWidthRadio) * initThumbWidth * percent
                    invalidate()
                }
//                expandThumbStartTime -> {
//
//                }
                in expandThumbStartTime..expandRingStartTime -> {
                    currentState = State.ExpandThumb
                    if (!expandThumbColorAnimator.isRunning) {
                        expandThumbColorAnimator.start()
                    }
                    calSpreadThumbs(animatedValue)
                    val percent = (animatedValue - expandThumbStartTime) / expandThumbDuration
                    curThumbWidth =
                        initThumbWidth * minThumbWidthRadio + (maxThumbWidthRadio - minThumbWidthRadio) * initThumbWidth * percent
                    expandThumbCurCircleR = inR * percent
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    invalidate()
                }

                in expandRingStartTime..reboundThumbStartTime -> {
                    currentState = State.ExpandRing
                    val percent: Float = (animatedValue - expandRingStartTime) / expandRingDuration
                    Timber.d("percent:$percent")
                    curRingR = inR * (1 + percent * 0.2f)
                    expandRingPaint.strokeWidth = initRingStrokeWidth * (1 - percent)
                    Timber.d("strokeWidth:${expandRingPaint.strokeWidth}")
                    invalidate()
                }
                in reboundThumbStartTime..reboundThumbStartTime + reboundThumbDuration - 1 -> {
                    currentState = State.ReboundThumb
                    val percent: Float =
                        (animatedValue - reboundThumbStartTime) / reboundThumbDuration
                    curThumbWidth =
                        initThumbWidth * maxThumbWidthRadio - (maxThumbWidthRadio - 1) * initThumbWidth * percent
                    Timber.d("curThumbWidth:${curThumbWidth}")
                    invalidate()
                }
                else -> {
                    currentState = State.Liked
                    restoreData()
                    invalidate()
                }

            }
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

    fun restoreData() {
//        currentState = State.UnLiked
        curThumbWidth = initThumbWidth
        addedNewThumNum = 0
    }


    fun retore() {
        drawRestore()
    }


    fun startClickAnim() {
//        resetState()
        restoreData()
        initSpreadThumbs()
        wholeAnimator.start()
    }


    fun startLongClickAnim() {
//        resetState()
        restoreData()
        initSpreadThumbs()
        wholeAnimator.start()
    }


}

