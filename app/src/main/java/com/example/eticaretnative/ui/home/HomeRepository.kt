package com.example.eticaretnative.ui.home

import com.example.eticaretnative.base.BaseRepository
import com.example.eticaretnative.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor (private val apiFactory: ApiFactory):BaseRepository() {

    suspend fun getCategory()=safeApiRequest {
        apiFactory.getAllCategories()
    }
}