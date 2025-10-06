package com.stdio.domain.usecases

import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import com.stdio.domain.repository.CoursesRepository
import javax.inject.Inject
import com.stdio.domain.mapper.CourseFavoritesMapper

class GetCoursesUseCase @Inject constructor(private val repository: CoursesRepository) {
    suspend operator fun invoke(): LoadableData<List<Course>> {
        val favorites =
            repository.getFavorites()
        val favoritesMap = hashMapOf<Int, Boolean>()
        favorites.forEach {
            favoritesMap[it.id] = true
        }
        val courses = repository.getCourses()
        return CourseFavoritesMapper.map(courses, favoritesMap)
    }
}