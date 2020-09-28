package com.example.littletreetest.utils

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): Gson {
        return Gson()
    }

}