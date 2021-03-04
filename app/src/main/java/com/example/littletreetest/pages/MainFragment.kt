package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMainBinding
import com.example.littletreetest.pages.free.Free
import com.example.littletreetest.pages.free.FreeFragmentDirections

class MainFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_main

    lateinit var binding: FragmentMainBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        binding.btnHhh.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToFreeFragment()
            val options = navOptions {
                popUpTo(R.id.mainFragment) {
                    inclusive = false
                }
                anim {
                    enter = R.anim.fragment_fade_enter
                    exit = R.anim.fragment_fade_exit
                    popEnter = R.anim.fragment_fade_enter
                    popExit = R.anim.fragment_fade_exit
                }
            }
            findNavController().navigate(action, options)
        }

//        val action = MainFragmentDirections.actionMainFragmentToGameFragment()
//        findNavController().navigate(action)\


//        val action = MainFragmentDirections.actionMainFragmentToRecyclerViewFragment()
//        findNavController().navigate(action)

        val action = MainFragmentDirections.actionMainFragmentToConstraintLayoutFragment()
        findNavController().navigate(action)
    }

}
