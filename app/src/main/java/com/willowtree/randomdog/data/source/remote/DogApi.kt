package com.willowtree.randomdog.data.source.remote

import com.willowtree.randomdog.data.Dog
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DogApi {

    companion object {
        const val BASE_URL = "https://random.dog/"
    }

    @GET("woof.json")
    fun getRandomDog(): Deferred<Dog>
}