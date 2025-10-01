package com.stdio.data.dto

import com.google.gson.annotations.SerializedName

data class CourseDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("rate")
    val rate: Double,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("hasLike")
    val hasLike: Boolean,

    @SerializedName("publishDate")
    val publishDate: String
)

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseDTO>
)