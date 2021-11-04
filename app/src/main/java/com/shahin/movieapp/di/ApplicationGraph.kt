package com.shahin.movieapp.di

import android.app.Application
import com.shahin.data.di.DataModule
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

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationGraph
    }

}