package com.example.littletreetest.pages.testthird.smartrefreshlayout

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFreeBinding
import com.example.littletreetest.databinding.FragmentSmartRefreshBinding
import com.example.littletreetest.pages.free.FreeAdapter
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SmartRefreshFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_smart_refresh

    lateinit var binding: FragmentSmartRefreshBinding

    private val smartRefreshVM: SmartRefreshViewModel by viewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSmartRefreshBinding.bind(view)
        binding.rvItem.adapter = FreeAdapter(mutableListOf("1","2","3","1","2","3","1","2","3","1","2","3"))
        binding.rvItemDown.adapter = FreeAdapter(mutableListOf("111","222","333"))
        binding.layoutRefresh.setOnRefreshListener {
            showToast("setOnRefreshListener")
            it.finishRefresh(1000)
        }
    }



}
