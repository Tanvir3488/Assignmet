package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.AdapterViewBindingAdapter.setOnItemSelectedListener
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.test.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navController = findNavController(R.id.nav_host_fragment_activity_main)


         binding.navView.setOnItemSelectedListener{
             Log.e("tea", it.title.toString() )
            when (it.itemId) {
                R.id.add_user -> {
                    navController.popBackStack(
                        R.id.add_user,
                        true
                    )
                    navController.navigate(
                        R.id.add_user
                    )
                }
                R.id.all_user -> {
                    navController.popBackStack(
                        R.id.all_user,
                        true
                    )
                    navController.navigate(
                        R.id.all_user
                    )
                }

            }
            false
        }
    }
}