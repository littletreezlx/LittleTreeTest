package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment

class MainFragment : BaseFragment() {

    override val layoutId = R.layout.main_fragment

    override fun initView(view: View, savedInstanceState: Bundle?) {
        findNavController().navigate(R.id.action_mainFragment_to_testFragment)
    }

}
