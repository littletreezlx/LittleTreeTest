package com.example.littletreetest.pages.jingtu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.showToast
import com.example.littletreetest.databinding.FragmentDianzanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class DianzanFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_dianzan

    lateinit var binding: FragmentDianzanBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDianzanBinding.bind(view)
//        binding.viewLike.setOnClickListener {
//            binding.viewLike.isChecked = !binding.viewLike.isChecked
//        }
//        binding.btnRestoreLike.setOnClickListener {
//            binding.viewLike.isChecked = false
//        }
//        binding.viewDianzan.setOnClickListener {
//            Timber.d("touch:setOnClickListener")
//            binding.viewDianzan.startClickAnim()
//        }
        binding.viewDianzan.setOnTouchListener { v, event ->
            Timber.d("touch:setOnTouchListener")
//            binding.viewDianzan.startClickAnim()
            false
        }

        lifecycleScope.launch {
            delay(3000)
            showToast("HHHHH")
        }
//        binding.viewDianzan.setOnLongClickListener {
//            Timber.d("touch:longclick")
//            binding.viewDianzan.startLongClickAnim()
//            true
//        }
//        binding.btnRestoreDianzan.setOnClickListener {
//            binding.viewDianzan.retoreUnLiked()
//        }
    }


}
