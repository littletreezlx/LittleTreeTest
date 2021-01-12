package com.example.littletreetest.pages.mockjingtu

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentGameBottomPopupBinding
import com.mixu.jingtu.common.base.runOnce

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


//房间底部按钮弹出的Fragment Container
@AndroidEntryPoint
class GameBottomPopupFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_game_bottom_popup

    private lateinit var binding: FragmentGameBottomPopupBinding

    private val roomPagesVM: GamePagesViewModel by activityViewModels()

    private lateinit var roomBottomAdapter: FragmentStateAdapter

//    private var isFirstCreated = true


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentGameBottomPopupBinding.bind(view)
        roomBottomAdapter = UserRoomBottomAdapter(childFragmentManager, lifecycle)
        binding.vpRoomBottom.run {
            adapter = roomBottomAdapter
            isUserInputEnabled = false
            (getChildAt(0) as RecyclerView).setItemViewCacheSize(roomBottomAdapter.itemCount)
            (getChildAt(0) as RecyclerView).layoutManager?.isItemPrefetchEnabled = false
        }
        roomPagesVM.toTabAt.runOnce(viewLifecycleOwner) {
            Timber.d("toTabAt" + it)
            binding.vpRoomBottom.setCurrentItem(it, false)
        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
        } else {
            fakeOnResume()
        }
    }


    private fun fakeOnResume() {
//        if (isFirstCreated) {
//            isFirstCreated = false
//            return
//        }
        var tbdFragment = (roomBottomAdapter as UserRoomBottomAdapter).fragments.get(binding.vpRoomBottom.currentItem)
        tbdFragment?.let {
            it.isResumed
            if (it.isAdded) {
                it.onResume()
            }
        }
    }

}