package com.example.imagesearchapp.ui.gallery

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.domain.entity.UnsplashPhoto
import com.example.imagesearchapp.R
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentGalleryBinding
import com.example.imagesearchapp.util.PagingLoadStateAdapter
import com.example.imagesearchapp.util.onQueryTextSubmitListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment: BaseFragment<FragmentGalleryBinding>() ,GalleryAdapter.OnItemClickListener{

    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var adapter: GalleryAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGalleryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        subscribeOnPhotos()
        adapter = GalleryAdapter(this)
        binding?.apply {
            recyclerView.setHasFixedSize(true)
            // when we search the list flash on moment to avoid that
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter{ adapter.retry() },
                footer = PagingLoadStateAdapter{ adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        adapter.addLoadStateListener { loadState ->
            binding?.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && adapter.itemCount <1){
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                }else{
                    textViewEmpty.isVisible = false
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery ,menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextSubmitListener { query ->
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchPhotos(query)
            searchView.clearFocus()
        }

    }

    private fun subscribeOnPhotos(){
        viewModel.photos.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle ,it)
        }
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        findNavController().navigate(action)
    }

}