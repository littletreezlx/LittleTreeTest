package com.example.littletreetest.pages.uicomponent.recyclerview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class RecyclerViewFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_recyclerview

    lateinit var binding: FragmentRecyclerviewBinding

    private val vm: RecyclerViewViewModel by viewModels()

    private val testAdapter by lazy {
        TestAdapter(mutableListOf()).apply {
            setDiffCallback(TestDiffUtil())
        }
    }


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRecyclerviewBinding.bind(view)
        initRv()
        vm.getData(testAdapter)
        setClicks()
    }


    private fun setClicks() {
        binding.btnRefresh.setOnClickListener {
            vm.getData(testAdapter)
        }
        binding.btnAdd.setOnClickListener {
            vm.addOneData()
        }
        binding.btnRemove.setOnClickListener {
            vm.removeOneData()
        }
        binding.btnTimer.setOnClickListener {
            vm.startFakeMessageReceiver()
        }
        binding.btnRemoveHistory.setOnClickListener {
            vm.removeHistory()
        }
    }


    private fun initRv() {
        binding.rvTest.run {
            isNestedScrollingEnabled = false
            adapter = testAdapter
            itemAnimator = DefaultItemAnimator()
            val lm = LinearLayoutManager(context)
//            lm.stackFromEnd = true
            layoutManager = lm
//            layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
//            addOnScrollListener(
//                object : RecyclerView.OnScrollListener() {
//                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                        super.onScrollStateChanged(recyclerView, newState)
//                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                            Glide.with(this@RecyclerViewFragment).resumeRequests()
//                        }else {
//                            Glide.with(this@RecyclerViewFragment).pauseRequests()
//                        }
//                    }
//                }
//            )
        }
        vm.datas.observe(viewLifecycleOwner) {
            Timber.d("TestAdapter:size:${it.size}")
            testAdapter.setList(it)
        }
        vm.onDataAdded.observe(viewLifecycleOwner) {
            Timber.d("TestAdapter: onDataAdded")
//            Timber.d("${binding.rvTest.childCount}")
            Timber.d("${testAdapter.data.size}")
            testAdapter.notifyItemInserted(testAdapter.data.size - 1)
            binding.rvTest.scrollToPosition(testAdapter.data.size - 1)
        }
        vm.onDataRemoved.observe(viewLifecycleOwner) {
            testAdapter.removeAt(testAdapter.data.size - 1)
        }
        vm.onHistoryRemoved.observe(viewLifecycleOwner) {
            testAdapter.notifyItemRangeRemoved(0, it)
        }
    }


    private fun setLoadMore() {

    }


}
