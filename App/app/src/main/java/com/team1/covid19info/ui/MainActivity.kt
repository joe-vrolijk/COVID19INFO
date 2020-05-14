package com.team1.covid19info.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.team1.covid19info.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tbToolbar.setNavigationIcon(R.drawable.ic_covid19_black_100dp)
        initNavigation()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(navView, navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            tbTitle.text = destination.label
            tbTitle.setTextColor(resources.getColor(R.color.colorTitle))
        }
    }
}
