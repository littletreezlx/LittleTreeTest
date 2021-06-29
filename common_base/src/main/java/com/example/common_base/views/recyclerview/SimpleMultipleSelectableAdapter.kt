package com.example.common_base.views.recyclerview

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


// 只能多选的那种！！！
open class SimpleMultipleSelectableAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    list: MutableList<T>,
    val maxSelectable: Int = Int.MAX_VALUE
) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId, list) {


    companion object {
        private const val PAYLOADS_SELECTED = 0
        private const val PAYLOADS_UNSELECTED = 1
    }

    private var selectedSet = hashSetOf<Int>()

    private var itemClickListener: ((position: Int) -> Unit)? = null

    init {
        setOnItemClickListener { adapter, view, position ->
            if (isPositionSelected(position)) {
                setUnSelected(position)
            } else {
                setSelected(position)
            }
            itemClickListener?.invoke(position)
        }
    }


    override fun convert(holder: BaseViewHolder, item: T) {
        if (item.isSelected) {
            convertSelected(holder, item)
        } else {
            convertUnSelected(holder, item)
        }
    }


    override fun convert(holder: BaseViewHolder, item: T, payloads: List<Any>) {
        when (payloads[0]) {
            PAYLOADS_SELECTED -> {
                convertSelected(holder, item)
            }
            PAYLOADS_UNSELECTED -> {
                convertUnSelected(holder, item)
            }
        }
    }


    fun setSelected(position: Int) {
        if (selectedSet.size == maxSelectable) {
            return
        }
        selectedSet.add(position)
        notifyItemChanged(position, PAYLOADS_SELECTED)
    }


    fun setUnSelected(position: Int) {
//        if (selectedSet.size == maxSelectable){
//            return
//        }
        selectedSet.remove(position)
        notifyItemChanged(position, PAYLOADS_UNSELECTED)
    }


    fun setSelected(item: T) {
        if (selectedSet.size == maxSelectable) {
            return
        }
        val position = data.indexOf(item)
        selectedSet.add(position)
        notifyItemChanged(position, PAYLOADS_SELECTED)
    }


    fun setUnSelected(item: T) {
        val position = data.indexOf(item)
        selectedSet.remove(position)
        notifyItemChanged(position, PAYLOADS_UNSELECTED)
    }


    //需要全选的列表一定没有选择数量限制，先不改了
    fun selectAll() {
        for (i in 0 until itemCount) {
            selectedSet.add(i)
        }
        notifyItemRangeChanged(0, itemCount, PAYLOADS_SELECTED)
    }


//    fun selectAll(set: HashSet<Int>) {
//        for (i in set) {
//            selectedSet.add(i)
//        }
//        notifyItemRangeChanged(0, itemCount, PAYLOADS_SELECTED)
//    }


    fun selectPositions(set: HashSet<Int>) {
        for (i in set) {
            setSelected(i)
        }
    }


    fun unSelectAll() {
        selectedSet.clear()
        notifyItemRangeChanged(0, itemCount, PAYLOADS_UNSELECTED)
    }


    fun getSelectedPositionSet(): HashSet<Int> {
        return selectedSet
    }


    fun getSelectedItemSet(): HashSet<T> {
        val result = hashSetOf<T>()
        for (selectedPosition in selectedSet) {
            result.add(getItem(selectedPosition))
        }
        return result
    }


    //每次列表更改后，把之前选择的超出最新列表index范围的选项去掉
    override fun setList(list: Collection<T>?) {
        list?.let {
            for (selectedPosition in selectedSet) {
                if (selectedPosition > list.size) {
                    selectedSet.remove(selectedPosition)
                }
            }
        }
        super.setList(list)
    }


    open fun convertSelected(helper: BaseViewHolder, item: T) {}


    open fun convertUnSelected(helper: BaseViewHolder, item: T) {}


    fun setMultItemClickListener(listener: (position: Int) -> Unit) {
        this.itemClickListener = listener
    }


    fun isPositionSelected(position: Int): Boolean {
        return selectedSet.contains(position)
    }


    var T.isSelected: Boolean
        get() {
            return isPositionSelected(data.indexOf(this))
        }
        set(value) {
            if (value) {
                setSelected(data.indexOf(this))
            } else {
                setUnSelected(data.indexOf(this))
            }
        }


}