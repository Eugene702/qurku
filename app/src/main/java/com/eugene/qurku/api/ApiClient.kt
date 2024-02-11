package com.eugene.qurku.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://equran.id")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val eQuranApi: EQuranApi by lazy {
        retrofit.create(EQuranApi::class.java)
    }
}