package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {



    override val layoutId = R.layout.fragment_main

    lateinit var binding: FragmentMainBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        binding.btnHhh.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_freeFragment)

            findNavController().navigate(R.id.action_mainFragment_to_smartRefreshFragment)
        }
    }

}
