package com.stdio.data.mapper

import com.stdio.data.dto.CourseDTO
import com.stdio.data.dto.CoursesResponse
import com.stdio.domain.mapper.LoadableDataMapper
import com.stdio.domain.model.LoadableData

object CoursesResponseToCoursesListMapper : LoadableDataMapper<CoursesResponse, List<CourseDTO>>() {
    override fun getSuccess(input: LoadableData.Success<CoursesResponse>): List<CourseDTO> {
        return input.data.courses
    }
}