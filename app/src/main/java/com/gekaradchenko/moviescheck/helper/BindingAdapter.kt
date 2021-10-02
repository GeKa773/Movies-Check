package com.gekaradchenko.moviescheck.helper

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gekaradchenko.moviescheck.R

@BindingAdapter("android:load")
fun ImageView.load(url: String?) {
    if (url == null) {
        this.setImageResource(R.drawable.ic_anonymous)
        return
    }
    Glide.with(this.context)
        .load(url)
//        .centerCrop()
        .placeholder(R.drawable.ic_cinema)
        .into(this)
}

@BindingAdapter("android:setVisibility")
fun setVisibility(v: View, b: Boolean) {
    v.visibility = if (b) View.VISIBLE else View.GONE
}