package com.example.mobileauthorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileauthorize.adapters.FlashSaleAdapter
import com.example.mobileauthorize.databinding.FragmentMainPageBinding
import com.example.mobileauthorize.viewmodel.MainViewModel
import com.example.model.Product

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
        mainViewModel.getFlashSaleProductList()
        mainViewModel.latestAdapter.observe(viewLifecycleOwner) {
            latestRecyclerView.adapter = it
        }
        mainViewModel.flashSaleProductList.observe(viewLifecycleOwner) { productList ->
            flashSaleRecyclerView.adapter =
                this.activity?.supportFragmentManager?.let { fragmentManager ->
                    FlashSaleAdapter(
                        productList as ArrayList<Product>,
                        fragmentManager
                    )
                }
        }
    }
}