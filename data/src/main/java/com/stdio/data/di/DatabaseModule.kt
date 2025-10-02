package com.stdio.data.di

import android.content.Context
import androidx.room.Room
import com.stdio.data.dao.FavoritesDao
import com.stdio.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "courses.db"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(database: AppDatabase): FavoritesDao =
        database.favoritesDao()
}