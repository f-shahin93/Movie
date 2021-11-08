package com.shahin.movieapp.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    if (url == null) {
        imageView.setImageDrawable(null)
        return
    }
    Glide.with(imageView).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(imageView)
}