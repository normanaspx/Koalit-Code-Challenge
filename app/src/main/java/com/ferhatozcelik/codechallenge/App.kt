package com.ferhatozcelik.codechallenge

import android.app.Application
import com.ferhatozcelik.codechallenge.di.AppContext
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppContext.init(this) // Inicializa el contexto global
    }
}