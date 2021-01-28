package com.example.littletreetest.pages.free

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class TestSerializeable(

    var name: String = "init"

) : Parcelable