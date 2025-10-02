package com.stdio.data.mapper

import com.stdio.data.dto.CourseDTO
import com.stdio.domain.mapper.LoadableDataListMapper
import com.stdio.domain.model.Course

object CourseDTOToCourseMapper : CoursesAbstractListMapper() {
    override fun getSuccess(input: CourseDTO, favorites: Map<Int, Boolean>): Course = Course(
        id = input.id,
        title = input.title,
        text = input.text,
        price = input.price,
        rate = input.rate,
        startDate = input.startDate,
        // hasLike true НЕ ДОЛЖЕН приходить с сервера
        // в ТЗ написано про ЛОКАЛЬНУЮ БД
        hasLike = favorites[input.id] ?: false,
        publishDate = input.publishDate
    )
}