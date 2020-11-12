package com.example.drugstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.drugstore.fragments.DashboardFragment
import com.example.drugstore.fragments.SettingsFragment
import com.example.drugstore.fragments.ShoppingCartFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tienda.*


class Tienda : AppCompatActivity() {

    private val dashboardFragment = DashboardFragment()
    private val settingsFragment = SettingsFragment()
    private val shoppingCartFragment = ShoppingCartFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)
        replaceFragment(dashboardFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_dashboard -> replaceFragment(dashboardFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_shopping_cart -> replaceFragment(shoppingCartFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}