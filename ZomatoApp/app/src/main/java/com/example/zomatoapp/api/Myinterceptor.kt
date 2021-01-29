package com.example.zomatoapp.api

import okhttp3.Interceptor
import okhttp3.Response

class Myinterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept","application/json")
            .addHeader("user-key","ddbd4d09f67bd39cc8d20b84e6834f1f")
            .build()
        return chain.proceed(request)
    }
}