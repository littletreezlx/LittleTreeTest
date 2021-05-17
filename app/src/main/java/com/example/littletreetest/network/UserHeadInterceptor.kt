package com.example.littletreetest.network

import okhttp3.Interceptor
import okhttp3.Response


class UserHeadInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .header("Content-Type", "application/json")
            .header("charset", "utf-8")
            .build()
        return chain.proceed(request)
    }


}
