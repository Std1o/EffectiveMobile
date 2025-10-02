package com.stdio.effectivemobile.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stdio.core.di.CoreComponent
import com.stdio.data.di.DataComponent
import com.stdio.domain.repository.CoursesRepository
import com.stdio.effectivemobile.ui.home.HomeViewModel
import com.stdio.effectivemobile.MainActivity
import com.stdio.effectivemobile.ui.home.HomeFragment
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
    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(dataComponent: DataComponent): AppComponent
    }
}

@Module
class HomeModule {

    @Provides
    fun provideHomeViewModelFactory(
        repository: CoursesRepository
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(repository) as T
            }
        }
    }
}