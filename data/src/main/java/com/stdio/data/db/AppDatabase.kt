package com.stdio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stdio.data.dao.FavoritesDao
import com.stdio.data.entity.FavoriteCourseEntity

@Database(
    entities = [FavoriteCourseEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}