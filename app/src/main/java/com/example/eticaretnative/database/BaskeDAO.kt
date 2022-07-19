package com.example.eticaretnative.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.eticaretnative.model.ProductResponseModel

@Dao
interface BaskeDAO{

    @Insert
    suspend fun insertBasket(basketEntity: ProductResponseModel)

    @Query("Select * from basket_table")
    suspend fun readAllBasket():List<ProductResponseModel>

    @Update
    suspend fun updateBasket(productResponseModel: ProductResponseModel)

    @Query("DELETE FROM basket_table")
    suspend fun deleteBasket()

}