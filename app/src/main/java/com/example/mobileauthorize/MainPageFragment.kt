package com.example.mobileauthorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobileauthorize.databinding.ActivityLogInBinding
import com.example.mobileauthorize.databinding.FragmentMainPageBinding
import com.example.mobileauthorize.viewmodel.AuthorizeViewModel
import com.example.mobileauthorize.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        return binding.root
    }

    private fun initView() {
        mainViewModel.getCategoryAdapter()
        val categoryRecyclerView = binding.categoryRecyclerView
        val latestRecyclerView = binding.latestRecyclerView
        val flashSaleRecyclerView = binding.flashSaleRecyclerView
        mainViewModel.categoryAdapter.observe(viewLifecycleOwner) {
            categoryRecyclerView.adapter = it
        }
        mainViewModel.getLatestProductAdapter()
        mainViewModel.getFlashSaleProductAdapter()
        mainViewModel.latestAdapter.observe(viewLifecycleOwner) {
                latestRecyclerView.adapter = it
        }
        mainViewModel.flashSaleAdapter.observe(viewLifecycleOwner) {
            flashSaleRecyclerView.adapter = it
        }
    }
}