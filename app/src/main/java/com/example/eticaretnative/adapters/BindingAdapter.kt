package com.example.eticaretnative.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.eticaretnative.utils.Extensions.loadImageUrl

object BindingAdapter {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(imageView: ImageView,name:String){
        imageView.loadImageUrl(name)
    }
}