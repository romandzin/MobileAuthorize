package com.example.model

import com.google.gson.annotations.SerializedName

class FlashSaleProductList {
    @SerializedName("flash_sale")
    lateinit var product: MutableList<Product>
}