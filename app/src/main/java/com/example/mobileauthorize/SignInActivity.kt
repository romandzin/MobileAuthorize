package com.example.mobileauthorize

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobileauthorize.databinding.ActivitySignInBinding
import com.example.mobileauthorize.viewmodel.AuthorizeViewModel
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var loginViewModel: AuthorizeViewModel
    lateinit var firstName: String

    lateinit var logInText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loginViewModel =  ViewModelProvider(this)[AuthorizeViewModel::class.java]
        initView()
    }

    private fun initView() {
        logInText = binding.logInText
        logInText.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        binding.signInButton.setOnClickListener {
            loginViewModel.errorMessage.observe(this@SignInActivity) { value ->
                if (value != "") {
                    Toast.makeText(this@SignInActivity, value, Toast.LENGTH_SHORT).show()
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
                loginViewModel.onSignInClicked(
                    firstName,
                    binding.lastNameField.text.toString(),
                    binding.emailField.text.toString()
                )
            }
        }
    }
}