package com.stdio.effectivemobile.model

import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData

data class CoursesUIState(val courses: LoadableData<List<Course>> = LoadableData.NoState)