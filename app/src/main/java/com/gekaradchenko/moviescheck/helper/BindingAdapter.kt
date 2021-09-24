package com.gekaradchenko.moviescheck.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gekaradchenko.moviescheck.R

@BindingAdapter("android:load")
fun ImageView.load( url: String) {
    Glide.with(this.context)
        .load(url)
//        .centerCrop()
        .placeholder(R.drawable.ic_cinema)
        .into(this)
}