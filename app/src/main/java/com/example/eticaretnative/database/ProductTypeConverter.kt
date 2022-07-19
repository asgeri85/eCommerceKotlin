package com.example.eticaretnative.database

import androidx.room.TypeConverter
import com.example.eticaretnative.model.ProductResponseModel
import com.example.eticaretnative.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductTypeConverter {

    val gson=Gson()

    @TypeConverter
    fun productToString(productResponseModel: ProductResponseModel):String{
        return gson.toJson(productResponseModel)
    }

    @TypeConverter
    fun stringToProduct(string: String):ProductResponseModel{
        val list = object : TypeToken<ProductResponseModel>() {}.type
        return gson.fromJson(string,list)
    }

    @TypeConverter
    fun ratingToString(rating : Rating):String{
        return gson.toJson(rating)
    }

    @TypeConverter
    fun stringToRating(string: String):Rating{
        val list = object : TypeToken<Rating>() {}.type
        return gson.fromJson(string,list)
    }

}