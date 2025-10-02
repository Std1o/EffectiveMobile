package com.stdio.data.repository

import com.stdio.data.dao.FavoritesDao
import com.stdio.data.mapper.CourseDTOToCourseMapper
import com.stdio.data.mapper.CourseEntityToCourseMapper
import com.stdio.data.mapper.CourseToCourseEntityMapper
import com.stdio.data.mapper.CoursesResponseToCoursesListMapper
import com.stdio.data.remote.CoursesRemoteDataSource
import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import com.stdio.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesRemoteDataSource: CoursesRemoteDataSource,
    private val favoritesDao: FavoritesDao
) : CoursesRepository {

    override suspend fun getCourses(): LoadableData<List<Course>> {
        // domain не должен ничего знать о DTO, поэтому придется написать немного логики здесь
        val favorites =
            favoritesDao.getAllFavorites().map(CourseEntityToCourseMapper::map)
        val favoritesMap = hashMapOf<Int, Boolean>()
        favorites.forEach {
            favoritesMap[it.id] = true
        }
        return CourseDTOToCourseMapper.map(
            CoursesResponseToCoursesListMapper.map(
                coursesRemoteDataSource.getCourses()
            ), favoritesMap
        )
    }

    override suspend fun addToFavorites(course: Course) {
        favoritesDao.addToFavorites(CourseToCourseEntityMapper.map(course.copy(hasLike = true)))
    }

    override suspend fun removeFromFavorites(course: Course) {
        favoritesDao.removeFromFavorites(CourseToCourseEntityMapper.map(course))
    }

    override suspend fun getFavorites() =
        favoritesDao.getAllFavorites().map(CourseEntityToCourseMapper::map)

    override suspend fun isCourseFavorite(courseId: Int): Boolean {
        return favoritesDao.isFavorite(courseId) > 0
    }
}