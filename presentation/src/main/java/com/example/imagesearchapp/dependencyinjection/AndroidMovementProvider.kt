package com.example.imagesearchapp.dependencyinjection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.domain.intractor.GetUnsplashPhotosUseCase
import com.example.domain.repository.IUnsplashRepository
import dagger.hilt.android.EntryPointAccessors

/*
class AndroidMovementProvider(application: Application) : AndroidViewModel(application), RepoDependencyProvider {


    override val myRepo: IUnsplashRepository
        get() = EntryPointAccessors.fromApplication(getApplication() ,IRepositoryEntryPoint::class.java).getIUnsplashRepository()

    internal val getData: GetUnsplashPhotosUseCase by lazy {
        GetUnsplashPhotosUseCase(myRepo)
    }

}*/
