package com.stdio.effectivemobile.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stdio.domain.model.Course

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.text == newItem.text &&
                oldItem.price == newItem.price &&
                oldItem.rate == newItem.rate &&
                oldItem.startDate == newItem.startDate &&
                oldItem.publishDate == newItem.publishDate &&
                oldItem.hasLike == newItem.hasLike
    }

    override fun getChangePayload(oldItem: Course, newItem: Course): Any? {
        return if (oldItem.hasLike != newItem.hasLike) {
            mapOf("hasLike" to newItem.hasLike)
        } else {
            null
        }
    }
}