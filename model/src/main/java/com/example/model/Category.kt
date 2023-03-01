package com.example.model

data class Category(val name: String, val image: Int)

class CategoriesList {

    fun returnCategoriesList(): ArrayList<Category> {
        return arrayListOf(
            Category("Phones", R.drawable.ic_phone),
            Category("Headphones", R.drawable.ic_headphones),
            Category("Games", R.drawable.ic_games),
            Category("Cars", R.drawable.ic_cars),
            Category("Furniture", R.drawable.ic_furniture),
            Category("Kids", R.drawable.ic_kids),
        )}

}