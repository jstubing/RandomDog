package com.willowtree.randomdog.dog

import com.willowtree.randomdog.data.source.remote.DogApi
import javax.inject.Inject

class DogRepository @Inject constructor(private val dogApi: DogApi) {

    fun getRandomDog() = dogApi.getRandomDog()
}