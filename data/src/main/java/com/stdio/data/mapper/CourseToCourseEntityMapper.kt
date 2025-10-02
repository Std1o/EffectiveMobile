package com.stdio.data.mapper

import com.stdio.data.entity.FavoriteCourseEntity
import com.stdio.domain.mapper.Mapper
import com.stdio.domain.model.Course

object CourseToCourseEntityMapper : Mapper<Course, FavoriteCourseEntity> {
    override fun map(input: Course) = FavoriteCourseEntity(
        id = input.id,
        title = input.title,
        text = input.text,
        price = input.price,
        rate = input.rate,
        startDate = input.startDate,
        hasLike = input.hasLike,
        publishDate = input.publishDate
    )
}