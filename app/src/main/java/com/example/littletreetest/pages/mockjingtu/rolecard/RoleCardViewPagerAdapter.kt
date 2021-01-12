package com.example.littletreetest.pages.mockjingtu.rolecard

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.littletreetest.base.BaseFragment


class RoleCardViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val PAGE_ROLE_BACKGROUND = 0
        const val PAGE_ROLE_ABILITY = 1
    }

    val fragments: SparseArray<BaseFragment> = SparseArray()

    init {
        fragments.put(
            PAGE_ROLE_BACKGROUND,
            RoleBackgroundFragment()
        )
        fragments.put(
            PAGE_ROLE_ABILITY,
            RoleAbilityFragment()
        )
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}