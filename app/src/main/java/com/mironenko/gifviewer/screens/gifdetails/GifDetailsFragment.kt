package com.mironenko.gifviewer.screens.gifdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mironenko.gifviewer.KEY_GIF_URL
import com.mironenko.gifviewer.databinding.FragmentGifDetailsBinding

class GifDetailsFragment : Fragment() {
    private var _binding : FragmentGifDetailsBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    companion object {
        fun newInstance(url : String): GifDetailsFragment {
            val args = Bundle()
            args.putString(KEY_GIF_URL, url)
            val fragment = GifDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}