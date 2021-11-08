package com.shahin.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.data.model.DataResult
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.ui.detail.intent.MovieDetailViewIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val mainIntent = Channel<MovieDetailViewIntent>(Channel.CONFLATED)

    private val _state = MutableLiveData<MovieDetailViewState>()
    val state: LiveData<MovieDetailViewState> = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MovieDetailViewIntent.GetDetail -> {
                        getMovie(it.id)
                    }
                }
            }
        }
    }

    private fun getMovie(id: Long) {
        viewModelScope.launch {
            movieRepository.getMovie(id).collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        result.data?.let { data -> _state.postValue(MovieDetailViewState.Success(data)) }
                    }
                    is DataResult.Loading -> {
                        _state.postValue(MovieDetailViewState.IsLoading(result.data == null))
                    }
                    is DataResult.Error -> {
                        _state.postValue(MovieDetailViewState.Error(result.message ?: Throwable()))
                    }
                }
            }
        }
    }

}