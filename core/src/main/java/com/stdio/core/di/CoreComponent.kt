package com.stdio.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component()
interface CoreComponent {
    fun context(): Context

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ): CoreComponent
    }
}