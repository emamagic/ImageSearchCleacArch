package com.example.imagesearchapp.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentGalleryBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment: BaseFragment<FragmentGalleryBinding>() {

    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var adapter: UnsplashPhotoAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGalleryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeOnPhotos()
        adapter = UnsplashPhotoAdapter()
        binding?.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter{ adapter.retry() },
                footer = PagingLoadStateAdapter{ adapter.retry() }
            )
        }



    }

    private fun subscribeOnPhotos(){
        viewModel.photos.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle ,it)
        }
    }

}