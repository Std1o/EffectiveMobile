package com.stdio.data.mapper

import com.stdio.data.entity.FavoriteCourseEntity
import com.stdio.domain.mapper.Mapper
import com.stdio.domain.model.Course

object CourseEntityToCourseMapper : Mapper<FavoriteCourseEntity, Course> {
    override fun map(input: FavoriteCourseEntity) = Course(
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