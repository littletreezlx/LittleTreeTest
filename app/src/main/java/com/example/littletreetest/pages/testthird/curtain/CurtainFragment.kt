package com.example.littletreetest.pages.testthird.curtain

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseDialogFragment
import com.example.littletreetest.databinding.FragmentCurtainBinding
import com.mixu.jingtu.common.ext.dp
import com.qw.curtain.lib.Curtain
import com.qw.curtain.lib.Padding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurtainFragment : BaseDialogFragment() {


    override val layoutId = R.layout.fragment_curtain

    override val dialogType = DIALOG_TYPE_FULL_SCREEN

    lateinit var binding: FragmentCurtainBinding

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCurtainBinding.bind(view)
        Curtain(this)
            .with(binding.tvTest)
//            .withPadding(binding.tvTest, Padding.all(20.dp))
            .show()
    }


}
