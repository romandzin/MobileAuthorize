package com.example.model

import com.google.gson.annotations.SerializedName

class LatestProductList {
        @SerializedName("latest")
        lateinit var product: MutableList<Product>
}