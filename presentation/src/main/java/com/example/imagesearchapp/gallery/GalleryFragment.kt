package com.example.imagesearchapp.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.ApiWrapper
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GalleryFragment: BaseFragment<FragmentGalleryBinding>() {

    private val viewModel: GalleryViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGalleryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUnsplashPhotos()
        viewModel.getPhotos.observe(viewLifecycleOwner){ response ->

            when(response){
                is ApiWrapper.Success -> {
                    Log.e("TAG", "onViewCreated: s${response.data}", )
                }
                is ApiWrapper.ApiError -> {
                    Log.e("TAG", "onViewCreated: ${response.error}", )
                }
                is ApiWrapper.UnknownError -> {
                    Log.e("TAG", "onViewCreated: ${response.message}", )
                }
                is ApiWrapper.NetworkError -> {
                    Log.e("TAG", "onViewCreated: net ${response.message}", )
                }
            }
        }
    }

}