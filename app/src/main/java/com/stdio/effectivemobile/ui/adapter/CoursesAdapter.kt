package com.stdio.effectivemobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stdio.domain.model.Course
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.databinding.ItemCourseBinding
import java.text.SimpleDateFormat

class CoursesAdapter(
    private val onFavoriteClick: (Int, Boolean) -> Unit
) : ListAdapter<Course, RecyclerView.ViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onFavoriteClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CourseViewHolder).bind(getItem(position))
    }

    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onFavoriteClick: (Int, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            val context = binding.root.context
            binding.tvTitle.text = course.title
            binding.tvDescription.text = course.text
            binding.tvPrice.text = context.getString(R.string.price, course.price)
            binding.tvRating.text = course.rate
            binding.tvDate.text = formatDate(course.publishDate)

            // Обработка избранного
            //binding.favoriteButton.isSelected = course.hasLike
//            binding.favoriteButton.setOnClickListener {
//                val newFavoriteState = !course.hasLike
//                onFavoriteClick(course.id, newFavoriteState)
//            }
        }

        private fun formatDate(dateString: String): String {
            val inputForm = SimpleDateFormat("yyyy-MM-dd")
            val date = inputForm.parse(dateString)
            val outputFormat = SimpleDateFormat("dd MMMM yyyy")
            return outputFormat.format(date)
        }
    }
}