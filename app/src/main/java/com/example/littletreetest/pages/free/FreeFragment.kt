package com.example.littletreetest.pages.free

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
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
        binding.tvTest.setOnClickListener {
            freeVM.source0.value = 0

        }

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("Search: ${query}")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("Search: ${newText}")
                return false
            }

        })
        binding.searchView.setOnCloseListener {
            Timber.d("Search: onclose")
            false
        }


    }

}
