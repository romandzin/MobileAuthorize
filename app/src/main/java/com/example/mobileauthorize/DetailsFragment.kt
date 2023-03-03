package com.example.mobileauthorize

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileauthorize.adapters.PhotoDetailsAdapter
import com.example.mobileauthorize.databinding.FragmentDetailsBinding
import com.example.mobileauthorize.viewmodel.MainViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        return binding.root
    }

    private fun initView() {
        val photoRecyclerView = binding.mainPhotoRecyclerView
        mainViewModel.getProductDetailsList()
        mainViewModel.productDetails.observe(viewLifecycleOwner) { product ->
            var counter = 1
            photoRecyclerView.adapter = product.image_urls?.let { PhotoDetailsAdapter(it) }
            binding.productName.text = product.name
            binding.description.text = product.description
            binding.rating.text = product.rating.toString()
            binding.price.text = "$ ${product.price}"
            binding.reviews.text = "(${product.number_of_reviews} reviews)"
            binding.backButton.setOnClickListener {
                activity?.onBackPressed()
            }
            binding.color1.setColorFilter(Color.parseColor(product.colors?.get(0)))
            binding.color1.setOnClickListener {
                chooseColor(it as ImageView)
            }
            binding.color2.setColorFilter(Color.parseColor(product.colors?.get(1)))
            binding.color2.setOnClickListener {
                chooseColor(it as ImageView)
            }
            binding.color3.setColorFilter(Color.parseColor(product.colors?.get(2)))
            binding.color3.setOnClickListener {
                chooseColor(it as ImageView)
            }
            binding.finalCost.text = "$ ${counter * product.price!!}"
            binding.addButton.setOnClickListener {
                counter += 1
                binding.finalCost.text = "$ ${counter * product.price!!}"
            }
            binding.removeButton.setOnClickListener {
                counter -= 1
                binding.finalCost.text = "$ ${counter * product.price!!}"
            }
        }
    }

    private fun chooseColor(view: ImageView) {
        binding.color1.setBackgroundColor(resources.getColor(R.color.background_color))
        binding.color2.setBackgroundColor(resources.getColor(R.color.background_color))
        binding.color3.setBackgroundColor(resources.getColor(R.color.background_color))
        view.background = resources.getDrawable(R.drawable.choose_color_border)
    }
}