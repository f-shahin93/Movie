package com.shahin.movieapp.ui.movielist

import androidx.lifecycle.*
import com.shahin.data.model.Result
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.model.MainViewState
import com.shahin.movieapp.intent.MainViewIntent
import com.shahin.movieapp.model.SingleEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val mainIntent = Channel<MainViewIntent>(Channel.CONFLATED)

    private val _state = MutableLiveData<MainViewState>()
    val state: LiveData<MainViewState> = _state

    private val _singleState = MutableLiveData<SingleEvent<MainViewState>>()
    val singleState: LiveData<SingleEvent<MainViewState>> = _singleState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainViewIntent.GetList -> { getMovieList() }
                    is MainViewIntent.IsNavigateToDetail -> {
                        navigate(it.movieId)
                    }
                }
            }
        }
    }

    private fun getMovieList() {
        viewModelScope.launch {
            movieRepository.getTrendingMovieList().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.postValue(MainViewState.Success(result.data ?: emptyList()))
                    }
                    is Result.Loading -> {
                        _state.postValue(MainViewState.IsLoading(result.data == null))
                    }
                    is Result.Error -> {
                        _state.postValue(MainViewState.Error(result.message ?: Throwable()))
                    }
                }
            }
        }
    }

    private fun navigate(movieId: Long) {
        _singleState.value = SingleEvent(MainViewState.NavigateToDetail(movieId))
    }

}