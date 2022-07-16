package com.mironenko.gifviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mironenko.gifviewer.databinding.ActivityMainBinding
import com.mironenko.gifviewer.screens.giflist.GifGridFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, GifGridFragment.newInstance())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}