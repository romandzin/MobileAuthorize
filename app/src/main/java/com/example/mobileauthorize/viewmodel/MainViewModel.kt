package com.example.mobileauthorize.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.example.mobileauthorize.adapters.CategoryAdapter
import com.example.mobileauthorize.adapters.LatestAdapter
import com.example.model.AppDatabase
import com.example.model.CategoriesList
import com.example.model.Product
import com.example.model.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val resources = getApplication<Application>().resources
    val categoryAdapter = MutableLiveData<CategoryAdapter>()
    val latestAdapter = MutableLiveData<LatestAdapter>()
    val flashSaleProductList = MutableLiveData<MutableList<Product>>()
    val productDetails = MutableLiveData<Product>()
    private val retrofitService = RetrofitService.getRetrofitService()

    private var db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    private val userDao = db.userDao()

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

    fun getFlashSaleProductList() {
        viewModelScope.launch {
            val list: MutableList<Product>? = withContext(Dispatchers.IO) {
                retrofitService.getFlashSaleList().execute().body()?.product
            }
            flashSaleProductList.value = list
        }
    }

    fun getProductDetailsList() {
        viewModelScope.launch {
            val product: Product? = withContext(Dispatchers.IO) {
                retrofitService.getProductDetailsList().execute().body()
            }
            productDetails.value = product
        }
    }

    fun deleteUser(name: String) {
        viewModelScope.launch {
            val user = userDao.loadUserByFirstName(name)
            if (user != null) {
                userDao.delete(user)
            }
        }
    }
}