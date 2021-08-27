package com.irempakyurek.rickandmorty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object Utils {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }
}