package com.mironenko.gifviewer.screens.gifdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mironenko.gifviewer.R
import com.mironenko.gifviewer.databinding.FragmentGifDetailsBinding
import com.mironenko.gifviewer.factory
import com.mironenko.gifviewer.model.Gif

class GifDetailsFragment : Fragment() {
    private val args by navArgs<GifDetailsFragmentArgs>()
    private var _binding: FragmentGifDetailsBinding? = null
    private val mBinding get() = _binding!!
    private val viewModel: GifDetailsViewModel by viewModels { factory() }
    private lateinit var gifObserver: Observer<Gif>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.saveCurrentGifId(args.currentGifId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifDetailsBinding.inflate(inflater, container, false)

        gifObserver = Observer {
            Glide.with(requireContext())
                .load(it.downSized_large)
                .placeholder(R.drawable.progress_animated)
                .into(mBinding.ivGifDetails)

            mBinding.ivGifDetails.contentDescription = it.title
        }

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.gifDetails.observe(viewLifecycleOwner, gifObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.gifDetails.removeObserver(gifObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}