package com.example.littletreetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.random.Random

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

//    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vm =  ViewModelProvider(activity as ViewModelStoreOwner).get(ListViewModel::class.java)
        btn_show_toast.setOnClickListener {
            val random = Random.nextInt(100)
            vm.updateStr(random.toString())
        }

        vm._AllStr.observe(viewLifecycleOwner, Observer {
            tv_all_str.text = it
        })

        vm.subStr.observe(viewLifecycleOwner, Observer {
            tv_sub_str.text = it
        })

    }

    private fun generateData(): ArrayList<String> {
        val res = arrayListOf("1", "2", "3")
        return res
    }
}


fun showToast(message: String) {
    Toast.makeText(MyApp.context, message, Toast.LENGTH_SHORT).show()
}
