package com.stdio.data.mapper

import com.stdio.data.dto.CourseDTO
import com.stdio.domain.mapper.Mapper
import com.stdio.domain.model.Course

object CourseDTOToCourseMapper : Mapper<CourseDTO, Course> {
    override fun map(input: CourseDTO) = Course(
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