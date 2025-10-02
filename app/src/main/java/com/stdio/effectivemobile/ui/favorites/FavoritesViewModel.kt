package com.stdio.effectivemobile.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.domain.model.Course
import com.stdio.domain.repository.CoursesRepository
import com.stdio.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: CoursesRepository,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val favorites =
        repository.favorites.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
        }
    }
}