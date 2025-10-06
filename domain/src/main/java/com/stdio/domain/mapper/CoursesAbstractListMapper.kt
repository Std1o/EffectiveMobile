package com.stdio.domain.mapper

import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData

abstract class CoursesAbstractListMapper {
    fun map(
        input: LoadableData<List<Course>>,
        favorites: Map<Int, Boolean>
    ): LoadableData<List<Course>> =
        when (input) {
            is LoadableData.NoState -> LoadableData.NoState
            is LoadableData.Error -> LoadableData.Error(
                input.exception,
                input.code,
            )

            is LoadableData.Loading -> LoadableData.Loading
            is LoadableData.Success -> LoadableData.Success(input.data.map {
                getSuccess(it, favorites)
            })
        }

    protected abstract fun getSuccess(input: Course, favorites: Map<Int, Boolean>): Course
}