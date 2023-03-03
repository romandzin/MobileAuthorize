package com.example.mobileauthorize

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mobileauthorize.databinding.ActivityMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log


class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    lateinit var mainPageFragment: MainPageFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navigation = binding.bottomNav
        mainPageFragment = MainPageFragment()
        profileFragment = ProfileFragment()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(mainPageFragment, "main")
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.home -> {
                        loadFragment(mainPageFragment, "main")
                        return true
                    }
                    R.id.profile -> {
                        loadFragment(profileFragment, "profile")
                        return true
                    }
                }
                return false
            }
        }

    private fun loadFragment(fragment: Fragment, tag: String) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_content, fragment, tag)
        ft.addToBackStack(tag)
        ft.commit()
    }
    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.last()
        if (fragment is MainPageFragment) {

        }
        else supportFragmentManager.popBackStack()

    }


}