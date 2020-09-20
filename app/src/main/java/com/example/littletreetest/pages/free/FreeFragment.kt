package com.example.littletreetest.pages.free

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.base.SpUtil
import com.example.littletreetest.databinding.FragmentFreeBinding
import com.example.littletreetest.databinding.FragmentProgressbarBinding
import com.example.littletreetest.databinding.FragmentTestBinding
import com.example.littletreetest.databinding.FragmentViewpager2TablayoutBinding
import com.example.littletreetest.pages.ui.popupwindow.FunctionPopup
import com.example.littletreetest.pages.ui.popupwindow.StoryFilterPopup
import com.example.littletreetest.pages.ui.viewpager2tablayout.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mixu.jingtu.common.ext.dp2px
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment : BaseFragment() {

    @Inject
    lateinit var spUtil: SpUtil


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    private val freeVM: FreeViewModel by activityViewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)

        freeVM.test.observe(viewLifecycleOwner){
           binding.tvTest.text = it
        }

        binding.tvTest.setOnClickListener {
            freeVM.test()
        }

        Timber.d(spUtil.toString())
    }






}
