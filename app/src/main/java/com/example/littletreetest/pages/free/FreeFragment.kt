package com.example.littletreetest.pages.free

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFreeBinding
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    private val freeVM: FreeViewModel by navGraphViewModels(R.id.nav_main)


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)
        binding.vm = freeVM
        binding.lifecycleOwner = this

        showToast(freeVM.testNavGraphViewModels)
        binding.tvTest.setOnClickListener {
//            freeVM.source0.value = 0

            freeVM.testNavGraphViewModels = "sdaasdasdsa"
            findNavController().navigate(R.id.action_freeFragment_to_freeFragment2)
        }


    }

}
