package com.example.littletreetest.base

import android.content.Context
import android.content.SharedPreferences
import com.example.littletreetest.App

object SpUtil {

    private const val IS_GM = "IS_GM"

    fun getInstance(): SpUtil {
        return this
    }

    private val preferences: SharedPreferences by lazy {
        App.appContext.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    var isGm: Boolean
        get() {
            return preferences.getBoolean(IS_GM, false)
        }
        set(value) {
            preferences.edit().putBoolean(IS_GM, value).apply()
        }

}