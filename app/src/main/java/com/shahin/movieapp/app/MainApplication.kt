package com.shahin.movieapp.app

import android.app.Application
import com.shahin.movieapp.di.ApplicationGraph
import com.shahin.movieapp.di.DaggerApplicationGraph

class MainApplication : Application() {
    lateinit var applicationGraph: ApplicationGraph
        private set

    override fun onCreate() {
        super.onCreate()
        applicationGraph = DaggerApplicationGraph.builder()
            .application(this)
            .build()

        applicationGraph.inject(this)

    }

}