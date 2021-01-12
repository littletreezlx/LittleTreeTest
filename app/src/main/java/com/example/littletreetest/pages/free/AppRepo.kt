package com.example.littletreetest.pages.free

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.DataResult
import com.doFailure
import com.doSuccess
import com.mixu.jingtu.common.base.BaseRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AppRepo @Inject constructor(
    @ApplicationContext context: Context,
   ) : BaseRepo() {

}

