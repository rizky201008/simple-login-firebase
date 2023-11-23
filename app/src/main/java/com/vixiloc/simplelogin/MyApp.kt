package com.vixiloc.simplelogin

import android.app.Application
import com.vixiloc.simplelogin.di.firebaseModules
import com.vixiloc.simplelogin.di.repositoryModule
import com.vixiloc.simplelogin.di.useCaseModules
import com.vixiloc.simplelogin.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(firebaseModules, repositoryModule, viewModelModules, useCaseModules))
        }
    }
}