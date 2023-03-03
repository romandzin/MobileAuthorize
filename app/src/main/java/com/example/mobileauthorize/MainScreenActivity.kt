package com.example.mobileauthorize

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mobileauthorize.databinding.ActivityMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


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
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(mainPageFragment)
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.home -> {
                        loadFragment(mainPageFragment)
                        return true
                    }
                    R.id.profile -> {
                        loadFragment(profileFragment)
                        return true
                    }
                }
                return false
            }
        }

    private fun loadFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_content, fragment, "main")
        ft.addToBackStack("main")
        ft.commit()
    }


}