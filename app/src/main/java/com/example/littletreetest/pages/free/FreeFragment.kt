package com.example.littletreetest.pages.free

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.navigation.navOptions
import com.example.common_base.base.BaseFragment
import com.example.common_base.ext.showToast
import com.example.littletreetest.R
import com.example.littletreetest.databinding.FragmentFreeBinding
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

        freeVM.test()
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
