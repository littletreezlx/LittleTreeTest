package com.example.littletreetest.pages.mockjingtu.rolecard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseDialogFragment
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentRoomRoleCardBinding
import com.example.littletreetest.pages.mockjingtu.UserRoomBottomAdapter
import com.mixu.jingtu.common.component.GridLayoutDecoration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        refresh()
    }


    private fun refresh() {
//        val top = binding.fragmentTop.findFragment<RoleCardTopFragment>()
//        if (top.isAdded) {
//            top.onResume()
//        }
        refreshChildPages()
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
//        binding.vpRoleCard.registerOnPageChangeCallback(object :
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                Timber.d("onPageSelected: ${position}")
//                refreshChildPages()
//            }
//        })
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


    private fun setClicks() {
        topTitleAdapter.setSingleItemClickListener {
            binding.vpRoleCard.currentItem = it
        }
    }

}
