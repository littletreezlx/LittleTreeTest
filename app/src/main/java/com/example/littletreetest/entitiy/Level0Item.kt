package com.allen.kotlinapp.entity

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.littletreetest.ExpandableItemAdapter

/**
 * 文 件 名: Level0Item
 * 创 建 人: Allen
 * 创建日期: 2017/6/13 15:10
 * 修改时间：
 * 修改备注：
 */
data class Level0Item(var title: String) : AbstractExpandableItem<Level1Item>(), MultiItemEntity {

    override fun getItemType(): Int {
        return ExpandableItemAdapter.TYPE_LEVEL_ROLE_ATTR
    }

    override fun getLevel(): Int {
        return 0
    }
}
