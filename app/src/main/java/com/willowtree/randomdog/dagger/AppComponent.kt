package com.willowtree.randomdog.dagger

import com.willowtree.randomdog.dog.DogActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun inject(target: DogActivity)
}