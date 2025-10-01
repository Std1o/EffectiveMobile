package com.stdio.domain.repository

import com.stdio.domain.model.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
    suspend fun addToFavorites(courseId: Int)
    suspend fun removeFromFavorites(courseId: Int)
    suspend fun getFavoriteCourses(): List<Course>
    suspend fun isCourseFavorite(courseId: Int): Boolean
}