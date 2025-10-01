package com.stdio.effectivemobile.app

import android.app.Application
import com.stdio.core.di.CoreComponent
import com.stdio.core.di.DaggerCoreComponent
import com.stdio.data.di.DaggerDataComponent
import com.stdio.effectivemobile.di.AppComponent
import com.stdio.effectivemobile.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        // Инициализация графа зависимостей
        val coreComponent = DaggerCoreComponent.factory().create(this, this)
        val dataComponent = DaggerDataComponent.factory().create(coreComponent)
        //val domainComponent = DaggerDomainComponent.factory().create(dataComponent)

        appComponent = DaggerAppComponent.factory().create(dataComponent)
    }
}