package com.example.littletreetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.littletreetest.customview.VerifyCodeView.InputCompleteListener
import com.example.littletreetest.fragment.TestPopupFragment
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val testAdapter = ExpandableItemAdapter(generateData())

//        rv.run {
//            adapter = testAdapter
//           layoutManager = LinearLayoutManager(context)
//        }

        verify_code_view.setInputCompleteListener(object : InputCompleteListener {
            override fun inputComplete() {
                Toast.makeText(
                    context,
                    "inputComplete: " + verify_code_view.getEditContent(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun invalidContent() {}
        })



        TestPopupFragment().show(childFragmentManager, "testpopup")
    }

    private fun generateData(): ArrayList<String> {
        val res = arrayListOf("1", "2", "3")
        return res
    }

//    private fun generateData(): ArrayList<MultiItemEntity> {
////        val lv0Count = 9
////        val lv1Count = 3
////        val personCount = 5
//        val attrList = arrayOf("属性", "专精", "技能")
//        val nameList = arrayOf("力量", "冥界", "治理", "洞察")
//        val random = Random()
//        val res = ArrayList<MultiItemEntity>()
//        for (i in 0 until attrList.size) {
//            val lv0 = Level0Item("headview___" + attrList[i])
//            for (j in 0 until nameList.size) {
//                lv0.addSubItem(Level1Item(nameList[j]))
//            }
//            res.add(lv0)
//        }
//        return res
//    }
}


fun showToast(message: String) {
    Toast.makeText(MyApp.context, message, Toast.LENGTH_SHORT).show()
}
