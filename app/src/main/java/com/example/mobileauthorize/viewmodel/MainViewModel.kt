package com.example.mobileauthorize.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mobileauthorize.adapters.CategoryAdapter
import com.example.mobileauthorize.adapters.FlashSaleAdapter
import com.example.mobileauthorize.adapters.LatestAdapter
import com.example.model.CategoriesList
import com.example.model.Product
import com.example.model.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): AndroidViewModel(application) {

    val resources = getApplication<Application>().resources
    val categoryAdapter = MutableLiveData<CategoryAdapter>()
    val latestAdapter = MutableLiveData<LatestAdapter>()
    val flashSaleAdapter = MutableLiveData<FlashSaleAdapter>()
    val retrofitService =  RetrofitService.getRetrofitService()

    fun getCategoryAdapter() {
        categoryAdapter.value = CategoryAdapter(CategoriesList().returnCategoriesList(), resources)
    }

    fun getLatestProductAdapter() {
        viewModelScope.launch {
            val list: MutableList<Product>? = withContext(Dispatchers.IO) {
                retrofitService.getLatestProductsList().execute().body()?.product
            }
                latestAdapter.value = LatestAdapter(list as ArrayList<Product>)
        }
    }

    fun getFlashSaleProductAdapter() {
        viewModelScope.launch {
            val list: MutableList<Product>? = withContext(Dispatchers.IO) {
                retrofitService.getFlashSaleList().execute().body()?.product
            }
            flashSaleAdapter.value = FlashSaleAdapter(list as ArrayList<Product>)
        }
    }
}