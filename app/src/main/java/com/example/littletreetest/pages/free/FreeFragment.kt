package com.example.littletreetest.pages.free

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
        binding.vm = freeVM
        binding.lifecycleOwner = this

        binding.tvTest.setOnClickListener {
            freeVM.updateFree()
        }
        binding.btnJump.setOnClickListener {
            findNavController().navigate(R.id.action_freeFragment_to_freeFragment2)
        }

//        val endX = textView.paint.textSize * textView.text.length
//        val linearGradient = LinearGradient(
//            0f, 0f, endX, 0f,
//            Color.parseColor("#FFFF68FF"),
//            Color.parseColor("#FFFED732"),
//            Shader.TileMode.CLAMP
//        )
//        textView.paint.shader = linearGradient
//        textView.invalidate()

    }

}
