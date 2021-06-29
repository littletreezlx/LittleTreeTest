package com.example.common_base.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import com.jingtu.R


//能够设置最大高度
class MyNestedScrollView(context: Context, attrs: AttributeSet?) :
    NestedScrollView(context, attrs) {


    init {
        initView(context, attrs)
    }

    private var maxHeight = 0


    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.MyNestedScrollView, 0, 0)
            maxHeight =
                typedArray.getDimension(R.styleable.MyNestedScrollView_max_height, 0f).toInt()
//            maxHeight.let {
//                if (it != 0f) {
//                    maxHeight = it
//                }
//            }
            typedArray.recycle()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        if (maxHeight != 0) {
            try {
                newHeightMeasureSpec =
                    MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)

    }
}