package com.willowtree.randomdog.main

import android.app.Application
import com.willowtree.randomdog.dagger.AppComponent
import com.willowtree.randomdog.dagger.AppModule
import com.willowtree.randomdog.dagger.DaggerAppComponent

class RandomDogApplication : Application() {

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}