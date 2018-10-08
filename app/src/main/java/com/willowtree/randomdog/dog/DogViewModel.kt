package com.willowtree.randomdog.dog

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.willowtree.randomdog.R
import com.willowtree.randomdog.data.binding.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

class DogViewModel(
        application: Application,
        private val repository: DogRepository
) : AndroidViewModel(application), CoroutineScope {

    val imageUrl = ObservableField<String>()
    val requestInProgress = ObservableBoolean()
    val snackBarMessage = SingleLiveData<String>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun onGetDogClick() = getDog()

    fun getDog() = launch {
        requestInProgress.set(true)

        try {
            val dog = repository.getRandomDog().await()
            imageUrl.set(dog.url)
        } catch (exception: Exception) {
            snackBarMessage.value = getApplication<Application>().getString(R.string.failed_to_retrieve_dog)
        }

        requestInProgress.set(false)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @Singleton
    class Factory @Inject constructor(
            private val application: Application,
            private val repository: DogRepository
    ) :
            ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DogViewModel(application, repository) as T
        }
    }
}