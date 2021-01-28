package com.example.littletreetest.pages.jetpackcompose

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
import com.example.littletreetest.pages.free.*
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class JetpackComposeFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_free

    lateinit var binding: FragmentFreeBinding

    private val freeVM: FreeViewModel by navGraphViewModels(R.id.nav_main) {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var repo: FreeRepo

    //    private val freeVM: FreeViewModel by viewModels

    val args: FreeFragmentArgs by navArgs()


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFreeBinding.bind(view)
        freeVM.test()

        showToast("${args}")
        binding.tvTest.setOnClickListener {

        }

        Timber.d(repo.toString())
    }

}
