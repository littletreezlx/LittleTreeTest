package com.example.littletreetest.pages.jingtu

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentDianzanBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DianzanFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_dianzan

    lateinit var binding: FragmentDianzanBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDianzanBinding.bind(view)

//
//        binding.viewLike.setOnClickListener {
//            binding.viewLike.isChecked = !binding.viewLike.isChecked
//        }
//        binding.btnRestoreLike.setOnClickListener {
//            binding.viewLike.isChecked = false
//        }

        binding.viewDianzan.setOnClickListener {
            Timber.d("touch:click")
            binding.viewDianzan.startClickAnim()
        }
        binding.viewDianzan.setOnLongClickListener {
            Timber.d("touch:longclick")
            binding.viewDianzan.startLongClickAnim()
            true
        }


        binding.btnRestoreDianzan.setOnClickListener {
            binding.viewDianzan.retore()
        }
    }


}
