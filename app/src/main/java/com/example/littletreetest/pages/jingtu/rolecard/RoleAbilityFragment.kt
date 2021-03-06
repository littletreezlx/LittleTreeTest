package com.example.littletreetest.pages.jingtu.rolecard

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentRolecardChildBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoleAbilityFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_rolecard_child

    lateinit var binding: FragmentRolecardChildBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRolecardChildBinding.bind(view)
        binding.tvContent.text = "能力"

    }


    override fun onResume() {
        super.onResume()
    }

}
