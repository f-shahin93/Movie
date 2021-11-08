package com.shahin.movieapp.ui

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shahin.data.repository.MovieRepository
import com.shahin.movieapp.app.MainApplication
import javax.inject.Inject

class DataUpdateWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : Worker(appContext, workerParams) {

    @Inject
    lateinit var movieRepo: MovieRepository


    init {
        (applicationContext as MainApplication).applicationGraph.inject(this)
    }

    override fun doWork(): Result {
        val isUpdated = movieRepo.updateData()
        return when (isUpdated) {
            true -> {
                Result.success()
            }
            false -> {
                Result.failure()
            }
        }
    }


}