package com.stdio.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.stdio.data.entity.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>>

    @Insert
    suspend fun addToFavorites(favorite: FavoriteCourseEntity)

    @Delete
    suspend fun removeFromFavorites(favorite: FavoriteCourseEntity)

    @Query("SELECT COUNT(*) FROM favorites WHERE id = :id")
    suspend fun isFavorite(id: Int): Int
}