package com.example.littletreetest.pages.ui.constraintlayout

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentConstraintlayoutBinding
import com.example.littletreetest.pages.free.*
import com.example.littletreetest.pages.ui.recyclerview.RecyclerViewViewModel
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
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
