package com.example.littletreetest.pages.jingtu;

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.pages.jingtu.rolecard.RoleCardFragment
import com.example.littletreetest.pages.jingtu.rolecard.RoleItemsFragment


class UserRoomBottomAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {


    companion object {
        const val PAGE_ATTR = 0
        const val PAGE_ITEM = 1

    }


    val fragments: SparseArray<BaseFragment> = SparseArray()


    init {
        fragments.put(
            PAGE_ATTR,
            RoleCardFragment()
        )
        fragments.put(
            PAGE_ITEM,
            RoleItemsFragment()
        )
    }


    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}