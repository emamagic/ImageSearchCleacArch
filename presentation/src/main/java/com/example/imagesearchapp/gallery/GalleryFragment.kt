package com.example.imagesearchapp.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.ApiWrapper
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

/*        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingStatee.collect {
                if (it) showLoading()
                Log.e("TAG", "loadingStatee: $it")
            }
        }*/


        viewModel.getUnsplashPhotos()
        viewModel.getPhotos.observe(viewLifecycleOwner){ response ->
            api(response){
                toasty("salam")
            }
        }
    }



}