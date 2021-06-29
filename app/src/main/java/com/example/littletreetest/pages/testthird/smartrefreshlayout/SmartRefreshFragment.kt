package com.example.littletreetest.pages.testthird.smartrefreshlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.common_base.base.BaseDialogFragment
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.showToast
import com.example.common_base.ext.viewBinding
import com.example.littletreetest.databinding.FragmentSmartRefreshBinding
import com.example.littletreetest.pages.free.FreeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SmartRefreshFragment : BaseDialogFragment() {


    override val layoutId = R.layout.fragment_smart_refresh

    private val binding: FragmentSmartRefreshBinding by viewBinding()

    private val vm: SmartRefreshViewModel by viewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.vm = vm
//        binding.lifecycleOwner = this
        binding.rvItem.adapter =
            FreeAdapter(mutableListOf("1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"))
//        binding.layoutRefresh.setOnRefreshListener {
//            showToast("setOnRefreshListener")
//            it.finishRefresh(1000)
//        }

        binding.btnStart.setOnClickListener {
            vm.refreshing.setValue(true)
        }

        binding.btnStop.setOnClickListener {
            vm.refreshing.setValue(false)
        }

    }


}
