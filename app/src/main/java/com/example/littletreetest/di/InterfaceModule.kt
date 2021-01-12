package com.example.littletreetest.di

import android.content.Context
import com.example.littletreetest.pages.free.HiltInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
// 这里使用了 ApplicationComponent，因此 NetworkModule 绑定到 Application 的生命周期。
abstract class InterfaceModule {


//    @Binds
//    abstract fun provideInterface(
//        aa: Retrofit
//    ): HiltInterface

}