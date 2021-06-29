package com.example.littletreetest.pages.jetpackcompose

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFreeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JetpackComposeFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    //    private val freeVM: FreeViewModel by viewModels

//    val args: FreeFragmentArgs by navArgs()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)


    }


}
