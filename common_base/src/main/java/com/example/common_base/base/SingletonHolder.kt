package com.example.common_base.base

open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}

//open class SingletonHolder(private val creator: A.() -> T) {
//
//    private var instance: T? = null
//
//    fun getInstance(arg: A): T =
//        instance ?: synchronized(this) {
//            instance ?: creator(arg).apply { instance = this }
//        }
//}