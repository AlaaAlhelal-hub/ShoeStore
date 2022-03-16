package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.viewModel.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: SharedViewModel by viewModels()
    lateinit var  navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)




        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener {
             controller, destination, arguments ->
                when (destination.id) {
                    controller.graph.startDestination, R.id.welcomeFragment, R.id.instructionsFragment -> supportActionBar!!.hide()
                    R.id.listOfShoesFragment -> {
                        supportActionBar!!.show()
                        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                        supportActionBar!!.setDisplayShowCustomEnabled(false)
                    }
                    else -> {//Add new shoes
                        supportActionBar!!.show()
                        supportActionBar!!.setHomeButtonEnabled(true)
                        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                        supportActionBar!!.setDisplayShowCustomEnabled(true)
                    }
                }
            }
        setupActionBarWithNavController(navController, appBarConfiguration)


    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }



}

