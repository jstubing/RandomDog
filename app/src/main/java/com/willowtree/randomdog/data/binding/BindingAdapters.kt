package com.willowtree.randomdog.data.binding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.willowtree.randomdog.glide.GlideApp

@BindingAdapter("url")
fun loadImage(view: ImageView, url: String?) {
    when (url == null) {
        true -> GlideApp.with(view.context).clear(view)
        else -> GlideApp.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("visibilityBoolean")
fun setVisible(view: View, visible: Boolean) {
    when (visible) {
        true -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}