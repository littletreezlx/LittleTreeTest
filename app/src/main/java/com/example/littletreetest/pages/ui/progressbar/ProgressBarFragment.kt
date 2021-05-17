package com.example.littletreetest.pages.ui.progressbar

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentProgressbarBinding

class ProgressBarFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_progressbar

    lateinit var binding: FragmentProgressbarBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProgressbarBinding.bind(view)

    }


}
