package com.willowtree.randomdog.dog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.willowtree.randomdog.R
import com.willowtree.randomdog.databinding.ActivityDogBinding
import com.willowtree.randomdog.main.RandomDogApplication
import javax.inject.Inject

class DogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogBinding

    @Inject
    lateinit var factory: DogViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RandomDogApplication).appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dog)
        val viewModel = ViewModelProviders.of(this, factory).get(DogViewModel::class.java)
        binding.viewModel = viewModel
        subscribeToViewModel()
        binding.viewModel?.getDog()
    }

    private fun subscribeToViewModel() {
        binding.viewModel?.snackBarMessage?.observe(this, Observer { message ->
            Snackbar.make(binding.root, message!!, Snackbar.LENGTH_LONG).show()
        })
    }
}
