package com.shahin.movieapp.di.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.shahin.data.local.AppPrefManager
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.app.MainApplication
import com.shahin.movieapp.di.ApplicationGraph
import com.shahin.movieapp.ui.movielist.MovieListFragment
import com.shahin.movieapp.ui.movielist.MovieListViewModel
import dagger.*
import javax.inject.Inject
import javax.inject.Scope

@FeatureScope
@Component(
    dependencies = [ApplicationGraph::class],
    modules = [MovieListModule::class]
)
interface MovieListComponent {

    fun inject(movieListFragment: MovieListFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: ApplicationGraph): Builder

        @BindsInstance
        fun movieListFragment(movieListFragment: MovieListFragment): Builder
        fun build(): MovieListComponent
    }
}

@Module
class MovieListModule {
    @Provides
    @FeatureScope
    fun viewModel(
        fragment: MovieListFragment,
        factory: MovieListViewModelFactory,
    ): MovieListViewModel = fragment.createViewModel(factory = factory)
}

fun MovieListFragment.inject(): MovieListComponent {
    return DaggerMovieListComponent.builder()
        .appComponent((activity?.application as MainApplication).applicationGraph)
        .movieListFragment(this)
        .build()
}

class MovieListViewModelFactory @Inject constructor(
    private val movieRepo: MovieRepository,
    private val appPrefManager: AppPrefManager
) : ViewModelWithSavedStateFactory<MovieListViewModel> {
    override fun create(handle: SavedStateHandle): MovieListViewModel =
        MovieListViewModel(movieRepo, appPrefManager)
}

interface ViewModelWithSavedStateFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}

inline fun <reified VM : ViewModel> Fragment.createViewModel(
    factory: ViewModelWithSavedStateFactory<VM>,
): VM = GenericSavedStateViewModelFactory(factory, this).create(VM::class.java)

class GenericSavedStateViewModelFactory<out V : ViewModel>(
    private val viewModelFactory: ViewModelWithSavedStateFactory<V>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return viewModelFactory.create(handle) as T
    }
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope