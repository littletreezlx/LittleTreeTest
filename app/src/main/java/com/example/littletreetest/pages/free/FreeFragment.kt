package com.example.littletreetest.pages.free

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFreeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    private val freeVM: FreeViewModel by viewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)
        binding.vm = freeVM
        binding.lifecycleOwner = this

//        IncludeFreeTvBinding.bind(binding.includeTest)

        binding.tvTest.setOnClickListener {
//            freeVM.updateFree()

            freeVM.testList.value =mutableListOf<String>("111","222","333")

        }
        binding.btnJump.setOnClickListener {
//            findNavController().navigate(R.id.action_freeFragment_to_freeFragment2)

            freeVM.updateParentText()
        }

//        freeVM.testList.observe(viewLifecycleOwner){
//           freeVM.freeAdapter.setList(it)
//        }


//        freeVM.updateParentText()
    }

}
