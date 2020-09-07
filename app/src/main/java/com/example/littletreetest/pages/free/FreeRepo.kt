package com.example.littletreetest.pages.free

object FreeRepo {

    fun getInstance() = this

    val free: Free = Free("old")

}


data class Free(var name: String){

}