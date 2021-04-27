package com.example.littletreetest.pages.jingtu

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentDianzanBinding
import com.example.littletreetest.databinding.FragmentMockjingtuGameBinding
import com.example.littletreetest.pages.free.*
import com.mixu.jingtu.common.base.runOnce
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DianzanFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_dianzan

    lateinit var binding: FragmentDianzanBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDianzanBinding.bind(view)


        binding.viewLike.setOnClickListener {
            binding.viewLike.isChecked = !binding.viewLike.isChecked
        }
        binding.btnRestoreLike.setOnClickListener {
            binding.viewLike.isChecked = false
        }

        binding.viewDianzan.setOnClickListener {
            binding.viewDianzan.startSelectViewMotion()
        }
        binding.btnRestoreDianzan.setOnClickListener {
            binding.viewDianzan.retore()
        }
    }


}
