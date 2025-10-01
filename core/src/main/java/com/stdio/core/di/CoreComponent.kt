package com.stdio.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun context(): Context
    fun resources(): Resources
}

@Module
class CoreModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application = application

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideResources(context: Context): Resources = context.resources
}