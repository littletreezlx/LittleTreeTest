package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMainBinding
import com.example.littletreetest.pages.testthird.curtain.CurtainFragment
import com.example.littletreetest.pages.ui.jetpack.LifecycleFragment

class MainFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_main

    lateinit var binding: FragmentMainBinding


    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        binding.btnHhh.setOnClickListener {
//            val action = MainFragmentDirections.actionMainFragmentToFreeFragment()
//            val options = navOptions {
//                popUpTo(R.id.mainFragment) {
//                    inclusive = false
//                }
//            }
//            findNavController().navigate(action, options)


//            val action = MainFragmentDirections.actionMainFragmentToDianzanFragment()
//            findNavController().navigate(action)


            CurtainFragment().show(childFragmentManager, "111")

        }


        childFragmentManager.beginTransaction()
            .replace(R.id.root, LifecycleFragment()).commit()


//        Curtain(this)
//            .with(binding.btnHhh)
////            .withPadding(binding.tvTest, Padding.all(20.dp))
//            .show()
    }


}
