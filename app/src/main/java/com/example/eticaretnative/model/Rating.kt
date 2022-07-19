package com.example.eticaretnative.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val count: Int?,
    val rate: Double?
):Parcelable{
}