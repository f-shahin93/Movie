package com.shahin.movieapp.di

import dagger.Module

@Module(
    subcomponents = [
        MainActivitySubComponent::class
    ]
)
class SubComponentModule {}