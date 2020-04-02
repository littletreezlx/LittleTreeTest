package com.example.littletreetest.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.littletreetest.R
import com.example.littletreetest.TestPopup
import com.example.littletreetest.base.BaseDialogFragment
import kotlinx.android.synthetic.main.fragment_popup.*


class TestPopupFragment : BaseDialogFragment(){

    lateinit var popup: TestPopup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popup, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        popup.popupGravity = Gravity.START or Gravity.TOP
//        popup.showPopupWindow(tv_test)
        //        TestPopup(context).showPopupWindow(tv_test)

        popup = TestPopup(context)
//        popup.popupGravity = Gravity.BOTTOM

        popup.popupGravity = Gravity.CENTER


        btn_test.setOnClickListener {
            if (popup.isShowing){
                popup.dismiss()
            }else{
                popup.showPopupWindow(root_layout)

            }
        }
    }
}