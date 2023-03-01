package com.example.mobileauthorize.viewmodel

import android.R.attr.password
import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.mobileauthorize.SingleLiveData
import com.example.model.AppDatabase
import com.example.model.User


class AuthorizeViewModel(application: Application): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private var db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    private val userDao = db.userDao()

    private var userMutableLiveData: MutableLiveData<User>? = null
    var errorMessage = SingleLiveData<String>()

    suspend fun onSignInClicked(firstName: String, lastName: String, email: String) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            val user = User(firstName, lastName, email)
            val sameUser: User? = userDao.loadUserByFirstName(firstName)
            sameUser?.firstName?.let { Log.d("tag", it) }
            if (sameUser == null) {
                userMutableLiveData?.value = user
                userDao.insert(user)
                errorMessage.postValue("")
            }
            else errorMessage.postValue("You are already registered")
        }
       else errorMessage.postValue("Wrong email")
    }

    suspend fun onLogInClicked(firstName: String) {
        val sameUser: User? = userDao.loadUserByFirstName(firstName)
        if (sameUser == null) errorMessage.postValue("There is no account with this name")
        else errorMessage.postValue("")
    }
}