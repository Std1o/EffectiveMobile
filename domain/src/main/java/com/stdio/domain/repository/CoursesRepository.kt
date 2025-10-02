package com.stdio.domain.repository

import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData

interface CoursesRepository {
    suspend fun getCourses(): LoadableData<List<Course>>
    suspend fun addToFavorites(course: Course)
    suspend fun removeFromFavorites(course: Course)
    suspend fun getFavorites(): List<Course>
    suspend fun isCourseFavorite(courseId: Int): Boolean
}