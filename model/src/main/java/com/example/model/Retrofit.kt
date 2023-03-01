package com.example.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

object RetrofitService {
    fun getRetrofitService(): ApiService {
        val BASE_URL = "https://run.mocky.io/v3/"
        return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
    }
}