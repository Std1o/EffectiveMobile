package com.stdio.domain.usecases

import com.stdio.domain.model.Course
import com.stdio.domain.repository.CoursesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val repository: CoursesRepository) {
    suspend operator fun invoke(course: Course) {
        if (course.hasLike) {
            repository.removeFromFavorites(course)
        } else {
            repository.addToFavorites(course)
        }
    }
}