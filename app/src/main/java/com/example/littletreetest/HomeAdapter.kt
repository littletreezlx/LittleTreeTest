package com.example.littletreetest

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


//class HomeAdapter(): BaseQuickAdapter<TestPojo, BaseViewHolder>() {
////
////    override fun convert(helper: BaseViewHolder, item: HomeItem) {
////        helper.setText(R.id.text, item.getTitle())
////        helper.setImageResource(R.id.icon, item.getImageResource())
////    }
//
//    constructor(mList: List<TestPojo>?, mLayoutId: Int?) : super(mList)
//
//
//    override fun convert(helper: BaseViewHolder, item: TestPojo) {
//        helper.setText(R.id.tweetName, item.getUserName())
////            .setText(R.id.tweetText, item.getText())
//
//
//    }
//
//}
class HOmeAdapter(list: List<TestPojo>)
    : BaseQuickAdapter<TestPojo, BaseViewHolder>(R.layout.item_home, list) {

    override fun convert(helper: BaseViewHolder, item: TestPojo) {
        helper.setText(R.id.tv_name, item.name)
//        helper.setText(R.id.tweetText, "O ever youthful,O ever weeping")

    }


}