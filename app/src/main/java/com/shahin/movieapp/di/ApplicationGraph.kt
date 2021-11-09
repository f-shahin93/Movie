package com.shahin.movieapp.di

import android.app.Application
import com.shahin.data.di.DataModule
import com.shahin.data.local.AppPrefManager
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.app.MainApplication
import com.shahin.movieapp.ui.DataUpdateWorker
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        SubComponentModule::class,
        ViewModelModule::class,
        DataModule::class,
    ]
)
interface ApplicationGraph {

    fun mainComponent(): MainActivitySubComponent.Factory

    fun inject(worker: DataUpdateWorker)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationGraph
    }

    fun inject(application: MainApplication)

    fun exposMovieRepository(): MovieRepository
    fun exposAppPrefManager(): AppPrefManager

}