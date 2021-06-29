package com.example.littletreetest.test.scrollviewandviewpager2

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentTestTopBinding

class TopFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_test_top

    private lateinit var binding: FragmentTestTopBinding

    private var contentAdapter =
        TitleAdapter(
            mutableListOf()
        )


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTestTopBinding.bind(view)

    }


}

