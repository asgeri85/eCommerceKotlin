package com.example.eticaretnative.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "basket_table")
@Parcelize
data class ProductResponseModel(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?
):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var dbId:Int=0
    var count:Int=0
}