package com.example.eticaretnative.ui.products

import com.example.eticaretnative.base.BaseRepository
import com.example.eticaretnative.network.ApiFactory
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val apiFactory: ApiFactory):BaseRepository() {
    suspend fun getProducts(name:String)=safeApiRequest {
        apiFactory.getProductByCategory(name)
    }
}