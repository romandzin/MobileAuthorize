package com.example.mobileauthorize

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobileauthorize.databinding.ActivityLogInBinding
import com.example.mobileauthorize.viewmodel.AuthorizeViewModel
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    lateinit var loginViewModel: AuthorizeViewModel
    lateinit var firstName: String

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
                    intent.putExtra("name", firstName)
                    startActivity(intent)
                }
            }
            lifecycleScope.launch {
                firstName = binding.firstNameField.text.toString()
                loginViewModel.onLogInClicked(firstName)
            }
        }
    }
}