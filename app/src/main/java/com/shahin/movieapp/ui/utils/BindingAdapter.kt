package com.shahin.movieapp.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shahin.data.utils.Constant

@BindingAdapter("imageUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    if (url == null) {
        imageView.setImageDrawable(null)
        return
    }
    val completeUrl = Constant.imgUrl + url
    Glide.with(imageView).load(completeUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(imageView)
}