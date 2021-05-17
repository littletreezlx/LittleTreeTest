package com.example.littletreetest.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
// 这里使用了 ApplicationComponent，因此 NetworkModule 绑定到 Application 的生命周期。
abstract class InterfaceModule {


//    @Binds
//    abstract fun provideInterface(
//        aa: Retrofit
//    ): HiltInterface

}