package com.mironenko.gifviewer.screens.gifslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mironenko.gifviewer.databinding.FragmentGifsListBinding

class GifsListFragment : Fragment() {
    private var _binding: FragmentGifsListBinding? = null
    private val mBinding get() = _binding!!
    private val gifsAdapter = GifsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifsListBinding.inflate(inflater, container, false)

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerView.adapter = gifsAdapter
        return mBinding.root
    }

    companion object {
        fun newInstance() = GifsListFragment()
    }
}