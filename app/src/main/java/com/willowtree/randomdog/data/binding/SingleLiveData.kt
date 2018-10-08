package com.willowtree.randomdog.data.binding

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Variant of a LiveData which emits only when data is set. It will not emit on configuration change.
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private var pending = false

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, Observer { t ->
            if (pending) {
                pending = false
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(t: T?) {
        pending = true
        super.setValue(t)
    }

    fun call() {
        value = null
    }
}