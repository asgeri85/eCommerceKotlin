package com.example.eticaretnative.di

import android.content.Context
import androidx.room.Room
import com.example.eticaretnative.database.BasketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, BasketDatabase::class.java, "basket_table").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(database: BasketDatabase) = database.basketDao()
}