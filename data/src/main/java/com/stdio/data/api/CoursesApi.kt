package com.stdio.data.api

import com.stdio.data.dto.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoursesApi {

    @GET("courses")
    suspend fun getCourses(): Response<CoursesResponse>

    companion object {
        const val BASE_URL = "https://drive.usercontent.google.com/"
    }
}