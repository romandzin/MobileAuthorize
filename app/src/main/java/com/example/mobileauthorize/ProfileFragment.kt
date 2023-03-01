package com.example.mobileauthorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileauthorize.databinding.FragmentMainPageBinding
import com.example.mobileauthorize.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
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
    }
}