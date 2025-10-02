package com.stdio.data.remote

import com.stdio.data.api.CoursesApi
import com.stdio.data.base.BaseRemoteDataSource
import javax.inject.Inject

class CoursesRemoteDataSource @Inject constructor(private val coursesApi: CoursesApi) :
    BaseRemoteDataSource() {
    suspend fun getCourses() = loadData { coursesApi.getCourses() }
}