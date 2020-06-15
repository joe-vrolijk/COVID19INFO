package com.team1.covid19info.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.team1.covid19info.R
import com.team1.covid19info.ui.advice.AdviceViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adviceViewModel: AdviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
        loadFireBaseData()
    }

    fun setToolbarTitle(title: String) {
        tbTitle.text = title
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(navView, navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            setToolbarTitle(destination.label.toString())
            tbTitle.setTextColor(resources.getColor(R.color.colorTitle))
        }
    }

    private fun loadFireBaseData(){
        adviceViewModel= AdviceViewModel(this)
        adviceViewModel.getQuestionsFromFireBase()
    }

}
