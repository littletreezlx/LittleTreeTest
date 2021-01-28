package com.example.littletreetest.pages.ui.recyclerview

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.HiltViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.base.SpUtil
import com.example.littletreetest.databinding.FragmentFreeBinding
import com.example.littletreetest.databinding.FragmentRecyclerviewBinding
import com.example.littletreetest.pages.MainFragmentDirections
import com.example.littletreetest.pages.free.*
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class RecyclerViewFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_recyclerview

    lateinit var binding: FragmentRecyclerviewBinding

    private val vm: RecyclerViewViewModel by viewModels()

    private val testAdapter by lazy {
        TestAdapter(mutableListOf())
    }


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRecyclerviewBinding.bind(view)
        initRv()
        vm.getData()
        setClicks()
    }


    private fun setClicks(){
        binding.btnRefresh.setOnClickListener {
            vm.getData()
        }
    }


    private fun initRv(){
        binding.rvTest.run {
            adapter = testAdapter
            layoutManager = LinearLayoutManager(context)
        }
        vm.datas.observe(viewLifecycleOwner){
            Timber.d("TestAdapter:size:${it}")
            testAdapter.setList(it)
        }
    }

}
