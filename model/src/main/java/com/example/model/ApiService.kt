package com.example.model
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("https://run.mocky.io/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    fun getLatestProductsList(): Call<LatestProductList>

    @GET("https://run.mocky.io/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    fun getFlashSaleList(): Call<FlashSaleProductList>

    @GET("https://run.mocky.io/v3/f7f99d04-4971-45d5-92e0-70333383c239")
    fun getProductDetailsList(): Call<Product>
}