package com.example.littletreetest.pages.jingtu

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMockjingtuGameBinding
import com.mixu.jingtu.common.base.runOnce
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class GameFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_mockjingtu_game

    lateinit var binding: FragmentMockjingtuGameBinding

    private val roomPopupFragment by lazy { GameBottomPopupFragment() }

    private val gamePagesVM: GamePagesViewModel by activityViewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMockjingtuGameBinding.bind(view)
        childFragmentManager.beginTransaction()
            .replace(R.id.layout_container, roomPopupFragment)
            .commitAllowingStateLoss()

        gamePagesVM.toShowBottomPopup.runOnce(viewLifecycleOwner) {
            if (it) {
                Timber.d("BottomPopup: to Show")
                val ft = childFragmentManager.beginTransaction()
                roomPopupFragment.let { fragment ->
                    ft.show(fragment)
                    ft.commit()
                }
            } else {
                Timber.d("BottomPopup: to Hide")
                childFragmentManager.beginTransaction().hide(roomPopupFragment).commit()
                binding.layoutContainer.postDelayed({
                }, 200)

            }
        }
        binding.btnTab0.setOnClickListener {
            gamePagesVM.toShowBottomPopup(true)
            gamePagesVM.toTabAt(0)
        }
        binding.btnTab1.setOnClickListener {
            gamePagesVM.toShowBottomPopup(true)
            gamePagesVM.toTabAt(1)
        }
        binding.btnTabHide.setOnClickListener {
            gamePagesVM.toShowBottomPopup(false)
        }


        val svgOld = VectorDrawableCompat.create(resources, R.drawable.ic_icon_question, null)
        binding.ivSvg0.background = svgOld
        //你需要改变的颜色


        val svgNew = VectorDrawableCompat.create(resources, R.drawable.ic_icon_question, null)
        svgNew?.setTint(Color.parseColor("#FF0000"))
        binding.ivSvg1.background = svgNew
    }


}
