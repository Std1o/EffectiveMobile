package com.stdio.domain.repository

import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): LoadableData<List<Course>>
    suspend fun addToFavorites(course: Course)
    suspend fun removeFromFavorites(course: Course)
    suspend fun getFavoriteCourses(): Flow<List<Course>>
    suspend fun isCourseFavorite(courseId: Int): Boolean
}