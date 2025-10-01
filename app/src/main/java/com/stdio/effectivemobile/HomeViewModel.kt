package com.stdio.effectivemobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.domain.repository.CoursesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CoursesRepository) : ViewModel(){

    fun getData() {
        viewModelScope.launch {
            println(repository.getCourses())
        }
    }
}