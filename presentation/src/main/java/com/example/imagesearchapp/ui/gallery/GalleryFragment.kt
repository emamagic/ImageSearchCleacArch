package com.example.imagesearchapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

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
            api(response){
                toasty("salam")
            }
        }
    }



}