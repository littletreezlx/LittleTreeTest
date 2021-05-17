package com.example.littletreetest.pages.free

import android.content.Context
import com.mixu.jingtu.common.base.BaseRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepo @Inject constructor(
    @ApplicationContext context: Context,
) : BaseRepo() {

}

