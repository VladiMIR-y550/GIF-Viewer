package com.mironenko.gifviewer.screens.giflist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.mironenko.gifviewer.R
import com.mironenko.gifviewer.databinding.FragmentGifGridBinding
import com.mironenko.gifviewer.model.Gif
import com.mironenko.gifviewer.utils.internet.networkStatus
import com.mironenko.gifviewer.utils.*
import com.mironenko.gifviewer.utils.internet.NetworkStatus
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

class GifGridFragment : Fragment(),
    SearchView.OnQueryTextListener, MenuProvider {
    private var _binding: FragmentGifGridBinding? = null
    private val mBinding get() = _binding!!
    private val viewModel: GifGridViewModel by viewModels { factory() }
    private lateinit var gifObserver: Observer<Result<List<Gif>>>
    private lateinit var gifAdapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkStatus()
        if (savedInstanceState == null) {
            viewModel.downloadGif(START_PAGE, EMPTY_SEARCH_QUERY)
        }

        gifAdapter = GifAdapter(viewModel)
        gifAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifGridBinding.inflate(inflater, container, false)

        gifObserver = Observer {
            hideAll()
            when (it) {
                is SuccessResult -> {
                    gifAdapter.gifList = it.data
                }
                is PendingResult -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is EmptyResult -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is ErrorResult -> {
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.adapter = gifAdapter

        mBinding.swipeRefresh.setOnRefreshListener {
            viewModel.updateGifBase()
            mBinding.swipeRefresh.isRefreshing = false
        }

        activity?.addMenuProvider(this)

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
        activity?.removeMenuProvider(this)
        super.onDestroyView()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val searchView = menuItem.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isBlank()) {
            viewModel.downloadBySearchQueryGif(receivedSearchQuery = EMPTY_SEARCH_QUERY)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.isNotBlank()) {
            viewModel.downloadBySearchQueryGif(receivedSearchQuery = newText)
        }
        return true
    }

    private fun hideAll() {
        mBinding.swipeRefresh.isRefreshing = false
    }

    private fun checkNetworkStatus() {
        lifecycleScope.launchWhenStarted {
            requireContext().networkStatus.shareIn(lifecycleScope, SharingStarted.Eagerly, 1)
                .onEach {
                    if (it == NetworkStatus.Available) {
                        viewModel.downloadGif(START_PAGE, viewModel.searchQuery)
                    }
                }.collect()
        }
    }
}