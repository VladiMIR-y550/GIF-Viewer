package com.mironenko.gifviewer.screens.giflist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mironenko.gifviewer.GifViewerApp
import com.mironenko.gifviewer.databinding.FragmentGifListBinding
import com.mironenko.gifviewer.model.GifListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifListFragment : Fragment() {
    private var _binding: FragmentGifListBinding? = null
    private val mBinding get() = _binding!!
    private val gifsAdapter = GifAdapter()

    @Inject
    lateinit var repository: GifListRepository

    init {
        GifViewerApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifListBinding.inflate(inflater, container, false)

        lifecycleScope.launch(Dispatchers.IO) {
            repository.downloadGif(0)
        }

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerView.adapter = gifsAdapter
        return mBinding.root
    }

    companion object {
        fun newInstance() = GifListFragment()
    }
}