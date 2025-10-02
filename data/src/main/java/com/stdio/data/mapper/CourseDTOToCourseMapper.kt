package com.stdio.data.mapper

import com.stdio.data.dto.CourseDTO
import com.stdio.domain.mapper.LoadableDataListMapper
import com.stdio.domain.model.Course

object CourseDTOToCourseMapper : LoadableDataListMapper<CourseDTO, Course>() {
    override fun getSuccess(input: CourseDTO): Course = Course(
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