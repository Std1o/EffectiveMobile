package com.stdio.domain.mapper

import com.stdio.domain.model.Course

object CourseFavoritesMapper : CoursesAbstractListMapper() {
    override fun getSuccess(input: Course, favorites: Map<Int, Boolean>): Course = Course(
        id = input.id,
        title = input.title,
        text = input.text,
        price = input.price,
        rate = input.rate,
        startDate = input.startDate,
        hasLike = favorites[input.id] ?: false,
        publishDate = input.publishDate
    )
}