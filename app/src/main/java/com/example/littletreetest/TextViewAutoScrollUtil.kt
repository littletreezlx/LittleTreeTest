package com.example.littletreetest

import android.widget.TextView
import kotlin.concurrent.thread

object TextViewAutoScrollUtil {


    fun startTv(tv: TextView, str: String, space: Long) {
        val t = thread {
            var l = 1
            try {
                val max = str.length
                while (l <= max) {
                    val stv = str.substring(0, l)
                    tv.post {
                        tv.text = stv
                    }
                    Thread.sleep(space)
                    l++
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        tv.setOnClickListener {
            t.interrupt()
            tv.post {
                tv.text = str
            }
        }
    }


}