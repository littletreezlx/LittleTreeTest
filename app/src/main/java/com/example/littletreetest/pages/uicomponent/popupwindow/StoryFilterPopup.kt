package com.example.littletreetest.pages.uicomponent.popupwindow

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.common_base.ext.dp
import com.example.common_base.views.recyclerview.GridLayoutDecoration
import com.example.common_base.views.recyclerview.SimpleSingleSelectableAdapter
import com.example.littletreetest.R
import com.example.littletreetest.databinding.PopupStoryFilterBinding


class StoryFilterPopup(val context: Context) : BaseAnglePopupWindow() {

    private lateinit var filterAdapter: StoryFilterAdapter

    private lateinit var binding: PopupStoryFilterBinding

    init {
        contentView = initViews()
        isFocusable = true
    }

    override fun initViews(): View {
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_story_filter, null)
        binding = PopupStoryFilterBinding.bind(popupView)
        filterAdapter = StoryFilterAdapter(getLevelList())
        popupView.findViewById<RecyclerView>(R.id.rv_story_level).run {
            layoutManager = GridLayoutManager(context, 1)
            addItemDecoration(GridLayoutDecoration(3, 10.dp, 10.dp))
            adapter = filterAdapter
        }
        filterAdapter.setOnItemClickListener { adapter, view, position ->
//            val functionItem = filterAdapter.getItem(position)
//            dismiss()
        }
        return popupView
    }


    class StoryFilterAdapter(list: MutableList<String>) :
        SimpleSingleSelectableAdapter<String>(R.layout.item_popup_story_filter, list) {

        override fun convert(helper: BaseViewHolder, item: String) {
            helper.run {
                setText(R.id.tv_story_level, item)
            }
        }

        override fun convertSelected(helper: BaseViewHolder, item: String) {
            helper.run {
                setBackgroundResource(R.id.tv_story_level, R.drawable.background_3d4456_r4)
                setTextColor(R.id.tv_story_level, Color.parseColor("#FFFFFF"))
            }
        }

        override fun convertUnSelected(helper: BaseViewHolder, item: String) {
            helper.run {
                setBackgroundResource(R.id.tv_story_level, R.drawable.background_f3f4f5_4)
                setTextColor(R.id.tv_story_level, Color.parseColor("#111111"))
            }
        }
    }

    fun getLevelList() = mutableListOf<String>(
        "官方精品",
        "官方出品",
        "官方共享",
        "原创共享",
        "原创自用"
    )


    //用角的用这个
    //leftOff: popup最左边到anchorView最左边的距离
//    fun show(anchorView: View, leftOff: Int, topOff: Int) {
//        anchorView.post {
//            val location = IntArray(2)
//            anchorView.getLocationInWindow(location)
//            val anchorX: Int = location[0]
//            val anchorY: Int = location[1]
////            val windowHeight = contentView.measuredHeight
////            val windowWidth = contentView.measuredWidth
//            val x = anchorX - leftOff
//            val y = anchorY + anchorView.height + topOff
////            popup.updateAngleX(angleX)
//            showAtLocation(anchorView, Gravity.TOP or Gravity.LEFT, x, y)
//        }
//    }


}
