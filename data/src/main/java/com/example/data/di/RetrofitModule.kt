package com.example.data.di

import com.example.data.network.MyApi
import com.example.data.repository.UnsplashRepositoryImpl
import com.example.data.source.RemoteDataSource
import com.example.data.util.Constants.BASE_URL
import com.example.domain.repository.IUnsplashRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    fun provideIUnsplashRepository(remote: RemoteDataSource): IUnsplashRepository =
        UnsplashRepositoryImpl(remote)

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            //   .serializeNulls()
            .setLenient()
            .create()
    }

/*    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor {
        return Interceptor {
            val res = it.proceed(it.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.SECONDS)
                .build()

            res.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }*/

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Singleton
    @Provides
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(8000, TimeUnit.SECONDS)
            .writeTimeout(8000, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            //.cookieJar()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): MyApi {
        return retrofit.build().create(MyApi::class.java)
    }


}