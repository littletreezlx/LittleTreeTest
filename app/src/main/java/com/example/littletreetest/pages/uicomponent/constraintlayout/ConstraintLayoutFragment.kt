package com.example.littletreetest.pages.uicomponent.constraintlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentConstraintlayoutBinding
import com.example.littletreetest.pages.uicomponent.recyclerview.RecyclerViewViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConstraintLayoutFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_constraintlayout

    lateinit var binding: FragmentConstraintlayoutBinding

    private val vm: RecyclerViewViewModel by viewModels()

    lateinit var labelAdapter: LabelTagAdapter

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentConstraintlayoutBinding.bind(view)
        initViews()
        setClicks()
    }


    private fun initViews() {
    }


    private fun setClicks() {

    }


}
