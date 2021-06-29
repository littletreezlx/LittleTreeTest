package com.example.common_base.views.recyclerview

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import timber.log.Timber

// 需要点击效果的item太多了，自己封装一个，如果有复杂效果或逻辑就别用这个了
// 只能单选的那种！！！
//isAtLeastOneSelected : 是否至少要选择一个
open class SimpleSingleSelectableAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    list: MutableList<T>,
    private val isAtLeastOneSelected: Boolean = true
) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId, list) {

    var nowSelectedItemPosition = if (isAtLeastOneSelected) {
        0
    } else {
        -1
    }

    private var lastSelectedItemPosition = -1

    companion object {
        private const val PAYLOADS_SELECTED = 0
        private const val PAYLOADS_UNSELECTED = 1
    }


    init {
        setOnItemClickListener { adapter, view, position ->
            if (position == nowSelectedItemPosition) {
                setUnSelected(position)
            } else {
                setSelected(position)
            }
            itemClickListener?.invoke(position)
        }
    }


    override fun convert(holder: BaseViewHolder, item: T) {
        val position = holder.adapterPosition
        if (isPositionSelected(position)) {
            convertSelected(holder, item)
        } else {
            convertUnSelected(holder, item)
        }
    }


    override fun convert(holder: BaseViewHolder, item: T, payloads: List<Any>) {
        when (payloads[0]) {
            PAYLOADS_SELECTED -> {
                Timber.d("selected")
                convertSelected(holder, item)
            }
            PAYLOADS_UNSELECTED -> {
                Timber.d("unSelected")
                convertUnSelected(holder, item)
            }
        }
    }


    open fun convertSelected(holder: BaseViewHolder, item: T) {
    }


    open fun convertUnSelected(holder: BaseViewHolder, item: T) {
    }


    private var itemClickListener: ((position: Int) -> Unit)? = null


    private fun isPositionSelected(position: Int): Boolean {
        return position == nowSelectedItemPosition
    }


    //每次列表更改后，把之前选择的超出最新列表index范围的选项去掉
    override fun setList(list: Collection<T>?) {
        list?.let {
            if (nowSelectedItemPosition > list.size) {
                nowSelectedItemPosition = if (isAtLeastOneSelected) {
                    0
                } else {
                    -1
                }
            }
        }
        super.setList(list)
    }


    var T.isSelected: Boolean
        get() {
            return nowSelectedItemPosition == data.indexOf(this)
        }
        set(value) {
            if (value) {
                setSelected(data.indexOf(this))
            } else {
                setUnSelected(data.indexOf(this))
            }
        }


    open fun setSelected(position: Int) {
        lastSelectedItemPosition = nowSelectedItemPosition
        nowSelectedItemPosition = position
        notifyItemChanged(nowSelectedItemPosition, PAYLOADS_SELECTED)
        notifyItemChanged(lastSelectedItemPosition, PAYLOADS_UNSELECTED)
    }


    fun setUnSelected(position: Int) {
        if (isAtLeastOneSelected) {

        } else {
            if (isPositionSelected(position)) {
                lastSelectedItemPosition = position
                nowSelectedItemPosition = -1
                notifyItemChanged(lastSelectedItemPosition, PAYLOADS_UNSELECTED)
            }
        }
    }


    fun setUnSelectedAll() {
        lastSelectedItemPosition = nowSelectedItemPosition
        nowSelectedItemPosition = -1
        notifyItemChanged(lastSelectedItemPosition, PAYLOADS_UNSELECTED)
    }


    fun getSelectedItem(): T? {
        if (nowSelectedItemPosition == -1) {
            return null
        } else {
            return getItem(nowSelectedItemPosition)
        }
    }


    fun setSingleItemClickListener(listener: (position: Int) -> Unit) {
        this.itemClickListener = listener
    }

}