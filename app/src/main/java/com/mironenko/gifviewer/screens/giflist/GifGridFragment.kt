package com.mironenko.gifviewer.screens.giflist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mironenko.gifviewer.*
import com.mironenko.gifviewer.databinding.FragmentGifListBinding
import com.mironenko.gifviewer.model.Gif

class GifGridFragment : Fragment() {
    private var _binding: FragmentGifListBinding? = null
    private val mBinding get() = _binding!!
    private val viewModel: GifGridViewModel by viewModels { factory() }
    private lateinit var gifObserver: Observer<Result<List<Gif>>>
    private lateinit var gifAdapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifAdapter = GifAdapter()
        gifAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifListBinding.inflate(inflater, container, false)

        gifObserver = Observer {
            hideAll()
            when (it) {
                is SuccessResult -> {
                    gifAdapter.gifList = it.data
                }
                is PendingResult -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is EmptyResult -> mBinding.swipeRefresh.isRefreshing = true
            }
        }

        viewModel.downloadGif()

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        mBinding.recyclerView.adapter = gifAdapter
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.gifList.observe(viewLifecycleOwner, gifObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.gifList.removeObserver(gifObserver)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun hideAll() {
        mBinding.swipeRefresh.isRefreshing = false
    }

    companion object {
        fun newInstance() = GifGridFragment()
    }
}