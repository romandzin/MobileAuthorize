package com.example.mobileauthorize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobileauthorize.databinding.ActivityLogInBinding
import com.example.mobileauthorize.viewmodel.AuthorizeViewModel
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    lateinit var loginViewModel: AuthorizeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        loginViewModel = ViewModelProvider(this)[AuthorizeViewModel::class.java]
        setContentView(view)
        initView()
    }

    private fun initView() {
        binding.logInButton.setOnClickListener {
            loginViewModel.errorMessage.observe(this@LogInActivity) { value ->
                if (value != "") {
                    Toast.makeText(this@LogInActivity, value, Toast.LENGTH_SHORT).show()
                    loginViewModel.errorMessage.removeObservers(this)
                }
                else {
                    val intent = Intent(this, MainScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            lifecycleScope.launch {
                loginViewModel.onLogInClicked(binding.firstNameField.text.toString())
            }
        }
    }
}