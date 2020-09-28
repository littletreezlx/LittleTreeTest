package com.example.littletreetest.pages.free

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
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

    private val freeVM: FreeViewModel by activityViewModels()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)

        freeVM.test.observe(viewLifecycleOwner){
           binding.tvTest.text = it
        }

        binding.tvTest.setOnClickListener {

            freeVM.getFree()
//            freeVM.test()
        }

    }

}
