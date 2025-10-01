package com.stdio.data.di

import com.stdio.data.api.CoursesApi
import com.stdio.data.dao.FavoritesDao
import com.stdio.data.repository.CoursesRepositoryImpl
import com.stdio.domain.repository.CoursesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideCoursesRepository(
        api: CoursesApi,
        favoritesDao: FavoritesDao
    ): CoursesRepository = CoursesRepositoryImpl(api, favoritesDao)
}