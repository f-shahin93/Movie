package com.shahin.movieapp.di

import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

}