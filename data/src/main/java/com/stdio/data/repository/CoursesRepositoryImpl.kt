package com.stdio.data.repository

import com.stdio.data.api.CoursesApi
import com.stdio.data.dao.FavoritesDao
import com.stdio.data.entity.FavoriteCourseEntity
import com.stdio.data.mapper.CourseDTOToCourseMapper
import com.stdio.domain.model.Course
import com.stdio.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesApi: CoursesApi,
    private val favoritesDao: FavoritesDao
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> {
        return try {
            val response = coursesApi.getCourses()
            if (response.isSuccessful) {
                response.body()?.courses?.map(CourseDTOToCourseMapper::map) ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addToFavorites(courseId: Int) {
        favoritesDao.addToFavorites(FavoriteCourseEntity(courseId))
    }

    override suspend fun removeFromFavorites(courseId: Int) {
        favoritesDao.removeFromFavorites(FavoriteCourseEntity(courseId))
    }

    @Deprecated("Сделать реализацию")
    override suspend fun getFavoriteCourses(): List<Course> {
        return emptyList()
        /*return try {
            val allCourses = getCourses()
            val favoriteIds = favoritesDao.getAllFavorites().map { it.map { course -> course.courseId } }
            allCourses.filter { it.id in favoriteIds }
        } catch (e: Exception) {
            emptyList()
        }*/
    }

    override suspend fun isCourseFavorite(courseId: Int): Boolean {
        return favoritesDao.isFavorite(courseId) > 0
    }
}