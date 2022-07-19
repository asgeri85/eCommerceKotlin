package com.example.eticaretnative.utils

import android.widget.ImageView
import coil.load

object Extensions {

    fun ImageView.loadImageUrl(url: String?) {
        this.load(url) {
            crossfade(true)
            crossfade(500)
        }
    }

}

