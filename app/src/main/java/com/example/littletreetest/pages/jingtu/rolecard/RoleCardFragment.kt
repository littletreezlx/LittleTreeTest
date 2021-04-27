package com.example.littletreetest.pages.jingtu.rolecard

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentRoomRoleCardBinding
import com.mixu.jingtu.common.component.GridLayoutDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoleCardFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_room_role_card

    private lateinit var binding: FragmentRoomRoleCardBinding

    private lateinit var topTitleAdapter: RoleCardTitleAdapter

    private lateinit var viewPagerAdapter: RoleCardViewPagerAdapter


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRoomRoleCardBinding.bind(view)
        initTitleRecyclerView()
        initViewPager()
        setClicks()
    }


    override fun onResume() {
        super.onResume()
        refreshChildPages()
    }


    private fun refreshChildPages() {
        val position = binding.vpRoleCard.currentItem
        var tbdFragment = viewPagerAdapter.fragments.get(position)
        tbdFragment?.let {
            if (it.isAdded && it.isResumed) {
                it.onResume()
            }
        }
    }


    private fun initTitleRecyclerView() {
        topTitleAdapter = RoleCardTitleAdapter(
            mutableListOf("背景", "能力")
        )
        binding.rvTopTitle.run {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(
                GridLayoutDecoration(
                    2,
                    10, 0
                )
            )
            adapter = topTitleAdapter
        }
    }


    private fun initViewPager() {
        viewPagerAdapter = RoleCardViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )
        binding.vpRoleCard.run {
            adapter = viewPagerAdapter
            isUserInputEnabled = false
            (getChildAt(0) as RecyclerView).setItemViewCacheSize(viewPagerAdapter.itemCount)
            (getChildAt(0) as RecyclerView).layoutManager?.isItemPrefetchEnabled = false
        }
    }


    private fun setClicks() {
        topTitleAdapter.setSingleItemClickListener {
            binding.vpRoleCard.currentItem = it
        }
    }

}
