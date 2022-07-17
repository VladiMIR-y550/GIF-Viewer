package com.mironenko.gifviewer

import android.os.Bundle
import android.view.LayoutInflater
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
        val inflater = LayoutInflater.from(this)
        _binding = ActivityMainBinding.inflate(inflater)
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