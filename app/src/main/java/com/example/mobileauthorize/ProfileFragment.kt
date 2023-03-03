package com.example.mobileauthorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileauthorize.databinding.FragmentProfileBinding
import com.example.mobileauthorize.viewmodel.MainViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        return binding.root
    }

    fun initView() {
        binding.paymentMethod.buttonName.text = "Payment method"

        binding.tradeHistory.buttonName.text = "Trade history"

        binding.restorePurchase.buttonName.text = "Restore purchase"
        binding.restorePurchase.buttonIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_restore))

        binding.logOut.buttonName.text = "Log out"
        binding.logOut.buttonIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_log_out))

        binding.logOut.root.setOnClickListener {
            activity?.intent?.extras?.getString("name", "")
                ?.let { name -> mainViewModel.deleteUser(name) }
            activity?.finish()
        }
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}