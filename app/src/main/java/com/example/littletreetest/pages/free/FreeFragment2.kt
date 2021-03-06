package com.example.littletreetest.pages.free

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.littletreetest.R
import com.example.common_base.base.BaseFragment
import com.example.littletreetest.databinding.FragmentFree2Binding
import com.example.littletreetest.pages.SecondActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FreeFragment2 : BaseFragment() {

    override val layoutId = R.layout.fragment_free2

    lateinit var binding: FragmentFree2Binding

    private val freeVM: FreeViewModel by navGraphViewModels(R.id.nav_main) {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var repo: FreeRepo

    val args: FreeFragment2Args by navArgs()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFree2Binding.bind(view)

        val test = args.test
        Timber.d(test.toString())
        Timber.d(test.name)
        test.name = "ggg"

        binding.tvTest.setOnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))
        }
        Timber.d(repo.toString())

    }


}
