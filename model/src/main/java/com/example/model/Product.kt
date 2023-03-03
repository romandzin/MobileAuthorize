package com.example.model

data class Product(
    var name: String?,
    var description: String?,
    var rating: Double?,
    var number_of_reviews: Int?,
    var colors: ArrayList<String>?,
    var image_urls: ArrayList<String?>?,
    var category: String?,
    var image_url: String?,
    var price: Double?,
    var discount: Int?,
)
