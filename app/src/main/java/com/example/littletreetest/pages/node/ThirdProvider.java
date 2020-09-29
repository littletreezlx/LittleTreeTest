package com.example.littletreetest.pages.node;


import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.littletreetest.R;

import org.jetbrains.annotations.NotNull;

public class ThirdProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_third;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
    }
}
