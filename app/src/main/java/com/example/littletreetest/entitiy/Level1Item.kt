package com.allen.kotlinapp.entity

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.littletreetest.ExpandableItemAdapter
import com.example.littletreetest.PositionInList

/**
 * 文 件 名: Level1Item
 * 创 建 人: Allen
 * 创建日期: 2017/6/13 15:10
 * 修改时间：
 * 修改备注：
 */

data class Level1Item(var title: String) : MultiItemEntity, PositionInList
//    : AbstractExpandableItem<Person>(), MultiItemEntity
{
    override fun getItemType(): Int {
        return ExpandableItemAdapter.TYPE_LEVEL_ROLE_ATTR_CONCRETE
    }
//    override fun getLevel(): Int {
//        return 1
//    }


    override var parentPositionInData = 0
}
