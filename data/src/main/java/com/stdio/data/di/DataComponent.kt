package com.stdio.data.di

import com.stdio.core.di.CoreComponent
import com.stdio.data.api.CoursesApi
import com.stdio.data.dao.FavoritesDao
import com.stdio.domain.repository.CoursesRepository
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class],
    dependencies = [CoreComponent::class]
)
interface DataComponent {
    fun coursesApi(): CoursesApi
    fun coursesRepository(): CoursesRepository
    fun favoritesDao(): FavoritesDao

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): DataComponent
    }
}