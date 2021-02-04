package com.example.ejercicio2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ejercicio2.R
import com.example.ejercicio2.ui.home.fragment.HomeFragment
import com.example.ejercicio2.ui.home.fragment.ProfileFragment
import com.example.ejercicio2.ui.home.fragment.SearchFragment
import com.example.ejercicio2.ui.home.fragment.ShoppingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val profileFragment = ProfileFragment()
        val shoppingFragment = ShoppingFragment()

        makeCurrentFragment(homeFragment)

        val bnv = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> makeCurrentFragment(homeFragment)
                R.id.menu_search -> makeCurrentFragment(searchFragment)
                R.id.menu_profile -> makeCurrentFragment(profileFragment)
                R.id.menu_shopping_bag -> makeCurrentFragment(shoppingFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            if (!fragment.isAdded) {
                add(R.id.container_fragments, fragment)
            }
            for (fm in supportFragmentManager.fragments) {
                hide(fm)
            }
            show(fragment)
            commit()
        }
}


