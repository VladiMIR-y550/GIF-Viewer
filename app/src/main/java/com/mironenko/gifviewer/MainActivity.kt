package com.mironenko.gifviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mironenko.gifviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setSupportActionBar(mBinding.toolBar)

        navController = Navigation.findNavController(this, R.id.nav_host)

        val appBarConfig = AppBarConfiguration(navController.graph)
        mBinding.toolBar.setupWithNavController(navController, appBarConfig)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}