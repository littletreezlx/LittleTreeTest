package com.example.littletreetest.test.scrollviewandviewpager2

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentBlankABinding

class BlankAFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_blank_a

    private lateinit var binding: FragmentBlankABinding

    private var contentAdapter = TitleAdapter(
        mutableListOf()
    )


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBlankABinding.bind(view)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.rvContent.run {
            layoutManager = LinearLayoutManager(context)
            adapter = contentAdapter
            setHasFixedSize(true)
        }
        val testList = mutableListOf<String>()
        for (i in 0..5) {
            testList.add(i.toString())
        }
        contentAdapter.setList(testList)
    }
}

