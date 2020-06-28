package com.example.littletreetest.test.scrollviewandviewpager2

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.ScreenUtils
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentScrollviewAndViewpager2Binding
import timber.log.Timber

class ScrollViewAndViewPager2Fragment : BaseFragment() {


    override val layoutId = R.layout.fragment_scrollview_and_viewpager2

    private lateinit var binding: FragmentScrollviewAndViewpager2Binding

    private var topTitleAdapter =
        TitleAdapter(
            mutableListOf("背景", "能力")
        )

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentScrollviewAndViewpager2Binding.bind(view)
        initTopFragment()
        initTitleRecyclerView()
        initViewPager()
        setClicks()
    }

    private fun initTopFragment() {
//        childFragmentManager.beginTransaction().add(R.id.fragment_top, TopFragment()).commit()
    }


    private fun initTitleRecyclerView() {
        binding.rvTopTitle.run {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topTitleAdapter
        }
    }


    private fun initViewPager() {
//        binding.rvTopTitle.post {
//            val fragmentHeight = ScreenUtils.getAppScreenHeight() - binding.rvTopTitle.height
//            val lp = binding.vpRoleCard.layoutParams
//            lp.height = fragmentHeight
//            binding.vpRoleCard.layoutParams = lp
//        }
//
//
//        val viewPagerAdapter = RoleCardViewPagerAdapter(requireActivity())
//        binding.vpRoleCard.run {
//            adapter = viewPagerAdapter
//            isUserInputEnabled = false
//            (getChildAt(0) as RecyclerView).setItemViewCacheSize(viewPagerAdapter.itemCount)
//        }
    }

    private fun setClicks() {
        binding.rvTopTitle.post {
            val fragmentHeight = ScreenUtils.getAppScreenHeight() - binding.rvTopTitle.height
            val lp = binding.vpRoleCard.layoutParams
            lp.height = fragmentHeight
            binding.vpRoleCard.layoutParams = lp
        }

        childFragmentManager.beginTransaction().replace(R.id.vp_role_card, BlankAFragment()).commit()
        topTitleAdapter.setSingleItemClickListener {
            if (it == 0){
                childFragmentManager.beginTransaction().replace(R.id.vp_role_card, BlankAFragment()).commit()
            }else{
                childFragmentManager.beginTransaction().replace(R.id.vp_role_card, BlankBFragment()).commit()
            }
//            binding.vpRoleCard.currentItem = it
        }
    }


    class RoleCardViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        companion object {
            const val PAGE_ROLE_BACKGROUND = 0
            const val PAGE_ROLE_ABILITY = 1
        }

        val fragments: SparseArray<BaseFragment> = SparseArray()

        init {
            fragments.put(
                PAGE_ROLE_BACKGROUND,
                BlankAFragment()
            )
            fragments.put(
                PAGE_ROLE_ABILITY,
                BlankBFragment()
            )
        }

        override fun getItemCount(): Int {
            return fragments.size()
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}
