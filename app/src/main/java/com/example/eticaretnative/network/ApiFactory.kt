package com.example.eticaretnative.network

import com.example.eticaretnative.model.ProductResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFactory {

    @GET("products/categories")
    suspend fun getAllCategories():List<String>

    @GET("products/category/{category}")
    suspend fun getProductByCategory(@Path("category") category:String):List<ProductResponseModel>
}