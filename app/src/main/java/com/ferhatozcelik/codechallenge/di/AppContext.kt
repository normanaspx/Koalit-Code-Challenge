package com.ferhatozcelik.codechallenge.di


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContext {
    lateinit var context: Context

    fun init(app: Application) {
        context = app.applicationContext
    }
}
