package com.shahin.movieapp.ui.movielist

import android.content.Context
import androidx.lifecycle.*
import androidx.work.*
import com.shahin.data.local.AppPrefManager
import com.shahin.data.model.DataResult
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.model.MainViewState
import com.shahin.movieapp.intent.MainViewIntent
import com.shahin.movieapp.model.SingleEvent
import com.shahin.movieapp.ui.DataUpdateWorker
import com.shahin.movieapp.ui.utils.WORK_MANAGER_NAME
import com.shahin.movieapp.ui.utils.initialDelayPeriodicWorkRequest
import com.shahin.movieapp.ui.utils.repeatIntervalPeriodicWorkRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val appPrefManager: AppPrefManager
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
                    is MainViewIntent.SetupWorkManager -> checkSetupWorkManager(it.context)
                    is MainViewIntent.GetList -> { getMovieList() }
                    is MainViewIntent.IsNavigateToDetail -> {
                        navigate(it.movieId)
                    }
                }
            }
        }
    }

    private fun checkSetupWorkManager(context: Context) {
        val isOn = appPrefManager.getDailyDataUpdate()
        if (isOn.not()) {
            setupWorkManager(context)
        }
    }

    private fun setupWorkManager(context: Context) {
        val workManager = WorkManager.getInstance(context)
        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            DataUpdateWorker::class.java,
            repeatIntervalPeriodicWorkRequest,
            TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )
            .setInitialDelay(initialDelayPeriodicWorkRequest, TimeUnit.MINUTES)
            .build()

        appPrefManager.setDailyDataUpdate(true)
        workManager.enqueueUniquePeriodicWork(
            WORK_MANAGER_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest)
    }

    private fun getMovieList() {
        viewModelScope.launch {
            movieRepository.getTrendingMovieList().collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        _state.postValue(MainViewState.Success(result.data ?: emptyList()))
                    }
                    is DataResult.Loading -> {
                        _state.postValue(MainViewState.IsLoading(result.data == null))
                    }
                    is DataResult.Error -> {
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