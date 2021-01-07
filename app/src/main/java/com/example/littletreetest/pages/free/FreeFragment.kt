package com.example.littletreetest.pages.free

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
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.base.SpUtil
import com.example.littletreetest.databinding.FragmentFreeBinding
import com.example.littletreetest.pages.MainFragmentDirections
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    private val freeVM: FreeViewModel by navGraphViewModels(R.id.nav_main) {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var repo: FreeRepo

    //    private val freeVM: FreeViewModel by viewModels

    val args: FreeFragmentArgs by navArgs()


    var test = TestSerializeable("in free")


    override fun initView(view: View, savedInstanceState: Bundle?) {
        Timber.d(test.toString())
        Timber.d(test.name)
        binding = FragmentFreeBinding.bind(view)
        binding.vm = freeVM
        freeVM.test()

        binding.lifecycleOwner = this
        showToast("${args}")
        binding.tvTest.setOnClickListener {
//            Navte
            val action = FreeFragmentDirections.actionFreeFragmentToNavTestInclude(test)
            val options = navOptions {
//                popUpTo(R.id.nav_main) {
//                    inclusive = true
//                }

//                popUpTo(R.id.nav_main) {
//                    inclusive = true
//                }
            }
            findNavController().navigate(action, options)
        }

        Timber.d(repo.toString())
    }

}
