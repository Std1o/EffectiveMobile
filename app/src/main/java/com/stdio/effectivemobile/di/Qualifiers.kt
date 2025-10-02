package com.stdio.effectivemobile.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginViewModelFactory

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeViewModelFactory

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoritesViewModelFactory