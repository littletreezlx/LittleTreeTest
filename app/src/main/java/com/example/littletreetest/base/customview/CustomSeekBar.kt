package com.mixu.jingtu.common.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.SeekBar
import com.example.littletreetest.R
import com.mixu.jingtu.common.ext.dp2px
import timber.log.Timber

class CustomSeekBar : androidx.appcompat.widget.AppCompatSeekBar {

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }


    var thumbRadius = dp2px(17f)

    lateinit var thumbDrawable: Drawable


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        thumbRadius = heightSize


//        thumbDrawable = getNewDrawable(context, R.drawable.seekbar_thumb_3f3d56, thumbRadius, thumbRadius)

//        thumbDrawable = context.getDrawable(R.drawable.seekbar_thumb_3f3d56)!!
//        Timber.d("${dp2px(17f)}")
//        Timber.d("${dp2px(14f)}")
//        Timber.d("$heightSize")
    }


    private fun initView(context: Context, attrs: AttributeSet?) {
        setPadding(0, 0, 0, 0)
        progressDrawable = context.getDrawable(R.drawable.seekbar_style)
//        thumb = context.getDrawable(R.drawable.icon_circle_3f3d56_r14)
//        thumbDrawable = context.getDrawable(R.drawable.seekbar_thumb_3f3d56)!!
//        thumbDrawable = context.getDrawable(R.drawable.icon_circle_3f3d56)!!
//        thumb = null

//        progress = 1
//        max = 1
        thumb = null
        thumbOffset = 0

        splitTrack = false
        isEnabled = false

//        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (progress == 0 || progress == max) {
//                    thumb = null
//                } else {
//                    if (thumbRadius == dp2px(14f)) {
//                        thumb = context.getDrawable(R.drawable.icon_circle_3f3d56_r14)!!
//                    } else {
//                        thumb = context.getDrawable(R.drawable.icon_circle_3f3d56_r17)!!
//                    }
//
////                    if (thumbRadius == dp2px(14f)){
////                        thumb = thumb14
////                    }else{
////                        thumb = thumb17
////                    }
//                }
//                Timber.d("seekBar: ${progress} max: ${max} thumb: $thumb")
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//            }
//        })


//        val thumbDrawable = if (thumbRadius == dp2px(14f)) {
//            getContext().getDrawable(R.drawable.icon_circle_3f3d56_r14)
//        } else {
//            getContext().getDrawable(R.drawable.icon_circle_3f3d56_r17)
//        }
//
//        thumb = thumbDrawable

        setOnSeekBarChangeListener(OnCustomSeekBarChanged())
    }


    fun getNewDrawable(
        context: Context,
        restId: Int,
        dstWidth: Int,
        dstHeight: Int
    ): Drawable {
        val oldBitmap = BitmapFactory.decodeResource(context.resources, restId)
        val i = 1
        val scaledBitmap = Bitmap.createScaledBitmap(oldBitmap, dstWidth, dstHeight, true)
        return BitmapDrawable(resources, scaledBitmap)
    }


    class OnCustomSeekBarChanged() : OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//            if (progress == 0 || progress == seekBar?.max) {
//                seekBar?.thumb = null
//            } else {
//                seekBar?.thumb = thumbDrawable
//            }
            Timber.d("seekBar: ${progress} max: ${seekBar?.max}")
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }

    }
}