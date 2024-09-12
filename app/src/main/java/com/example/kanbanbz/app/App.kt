package com.example.kanbanbz.app

import android.app.Application
import com.example.kanbanbz.di.components.AppComponent
import com.example.kanbanbz.di.components.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}