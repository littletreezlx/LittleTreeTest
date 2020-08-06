package com.example.littletreetest.test.coordinatorLayout

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentCoordinatorLayoutBinding
import com.example.littletreetest.databinding.FragmentScrollviewAndViewpager2Binding
import com.example.littletreetest.test.scrollviewandviewpager2.TitleAdapter

class CoordinatorLayoutFragment : BaseFragment() {



    override val layoutId = R.layout.fragment_coordinator_layout

    private lateinit var binding: FragmentCoordinatorLayoutBinding

    private var contentAdapter = TitleAdapter(
        mutableListOf()
    )

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCoordinatorLayoutBinding.bind(view)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.rvContent.run {
            layoutManager = LinearLayoutManager(context)
            adapter = contentAdapter
        }
        val testList = mutableListOf<String>()
        for (i in 0..20){
            testList.add(i.toString())
        }
        contentAdapter.setList(testList)
    }

}

