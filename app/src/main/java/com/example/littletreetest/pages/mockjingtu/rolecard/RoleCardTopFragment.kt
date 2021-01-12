package com.example.littletreetest.pages.mockjingtu.rolecard

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
import com.example.littletreetest.databinding.FragmentRolecardChildBinding
import com.example.littletreetest.pages.MainFragmentDirections
import com.example.littletreetest.pages.free.*
import com.mixu.jingtu.common.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class RoleCardTopFragment : BaseFragment() {


    override val layoutId = R.layout.fragment_rolecard_child

    lateinit var binding: FragmentRolecardChildBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRolecardChildBinding.bind(view)
        binding.tvContent.text = "top"
    }


    override fun onResume() {
        super.onResume()
    }

}
