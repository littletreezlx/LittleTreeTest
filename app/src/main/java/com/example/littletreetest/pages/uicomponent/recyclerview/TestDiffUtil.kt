package com.example.littletreetest.pages.uicomponent.recyclerview

import androidx.recyclerview.widget.DiffUtil
import timber.log.Timber


class TestDiffUtil : DiffUtil.ItemCallback<RvItemDataModel>() {
    override fun areItemsTheSame(oldModel: RvItemDataModel, newModel: RvItemDataModel): Boolean {
        return oldModel.content == newModel.content
    }


    override fun areContentsTheSame(oldModel: RvItemDataModel, newModel: RvItemDataModel): Boolean {
        Timber.d("oldItem: ${oldModel}")
        Timber.d("newItem: ${newModel}")
        return oldModel.content == newModel.content
    }


}