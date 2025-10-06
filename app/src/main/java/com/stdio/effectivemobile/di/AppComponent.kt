package com.stdio.effectivemobile.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stdio.data.di.DataComponent
import com.stdio.domain.repository.CoursesRepository
import com.stdio.domain.usecases.GetCoursesUseCase
import com.stdio.domain.usecases.IsInputValidUseCase
import com.stdio.domain.usecases.ToggleFavoriteUseCase
import com.stdio.effectivemobile.ui.favorites.FavoritesFragment
import com.stdio.effectivemobile.ui.favorites.FavoritesViewModel
import com.stdio.effectivemobile.ui.home.HomeFragment
import com.stdio.effectivemobile.ui.home.HomeViewModel
import com.stdio.effectivemobile.ui.login.LoginFragment
import com.stdio.effectivemobile.ui.login.LoginViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HomeModule::class],
    dependencies = [DataComponent::class] // Зависим от DataComponent
)
interface AppComponent {
    fun inject(fragment: LoginFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {
        fun create(dataComponent: DataComponent): AppComponent
    }
}

@Module
class HomeModule {

    @Provides
    @HomeViewModelFactory
    fun provideHomeViewModelFactory(
        toggleFavoriteUseCase: ToggleFavoriteUseCase,
        getCoursesUseCase: GetCoursesUseCase
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(toggleFavoriteUseCase, getCoursesUseCase) as T
            }
        }
    }

    @Provides
    @LoginViewModelFactory
    fun provideLoginViewModelFactory(
        inputValidUseCase: IsInputValidUseCase
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(inputValidUseCase) as T
            }
        }
    }

    @Provides
    @FavoritesViewModelFactory
    fun provideFavoritesViewModelFactory(
        repository: CoursesRepository,
        toggleFavoriteUseCase: ToggleFavoriteUseCase
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoritesViewModel(repository, toggleFavoriteUseCase) as T
            }
        }
    }
}