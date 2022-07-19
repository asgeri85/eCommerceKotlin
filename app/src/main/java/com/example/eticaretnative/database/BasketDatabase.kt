package com.example.eticaretnative.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eticaretnative.model.ProductResponseModel

@Database(entities = [ProductResponseModel::class], version = 1, exportSchema = false)
@TypeConverters(ProductTypeConverter::class)
abstract class BasketDatabase :RoomDatabase() {

    abstract fun basketDao():BaskeDAO
}