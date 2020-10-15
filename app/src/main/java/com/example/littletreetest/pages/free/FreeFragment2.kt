package com.example.littletreetest.pages.free

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFree2Binding
import com.example.littletreetest.databinding.FragmentFreeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment2 : BaseFragment() {


    override val layoutId = R.layout.fragment_free2

    lateinit var binding: FragmentFree2Binding

    private val freeVM: FreeViewModel by activityViewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFree2Binding.bind(view)
        binding.vm = freeVM
        binding.lifecycleOwner = this

        binding.tvTest.setOnClickListener {
            freeVM.getFree()
        }

    }

}
