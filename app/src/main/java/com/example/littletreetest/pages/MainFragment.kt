package com.example.littletreetest.pages

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.littletreetest.R
import com.example.littletreetest.base.BaseFragment
import com.example.littletreetest.databinding.FragmentMainBinding
import com.example.littletreetest.pages.testthird.curtain.CurtainFragment
import com.example.littletreetest.test.scrollviewandviewpager2.BlankAFragment
import com.qw.curtain.lib.Curtain

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



//            childFragmentManager.beginTransaction()
//                .replace(R.id.root, CurtainFragment()).commit()

            CurtainFragment().show(childFragmentManager, "111")



        }

        Curtain(this)
            .with(binding.btnHhh)
//            .withPadding(binding.tvTest, Padding.all(20.dp))
            .show()
    }




}
