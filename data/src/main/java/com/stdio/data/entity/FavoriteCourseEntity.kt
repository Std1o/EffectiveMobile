package com.stdio.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCourseEntity(
    @PrimaryKey val courseId: Int,
    val addedDate: Long = System.currentTimeMillis()
)