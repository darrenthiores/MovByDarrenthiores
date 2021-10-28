package com.darrenthiores.movbybwa.Navigation

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.ActivityMainNavBinding

class MainNavActivity : AppCompatActivity() {

    private var _binding:ActivityMainNavBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main_nav)

        binding?.navView?.apply {
            this.itemIconTintList = null
            this.setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.navigation_home -> binding?.navView?.visibility = View.VISIBLE
                R.id.navigation_ticket -> binding?.navView?.visibility = View.VISIBLE
                R.id.navigation_profile -> binding?.navView?.visibility = View.VISIBLE
                else -> binding?.navView?.visibility = View.GONE
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}