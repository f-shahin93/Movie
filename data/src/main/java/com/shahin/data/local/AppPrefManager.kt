package com.shahin.data.local

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class AppPrefManager @Inject constructor(val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val DAILY_DATA_UPDATE_KEY = "daily_data_update"

    fun setDailyDataUpdate(value: Boolean) {
        sharedPreferences.edit().putBoolean(DAILY_DATA_UPDATE_KEY, value).apply()
    }

    fun getDailyDataUpdate(): Boolean =
        sharedPreferences.getBoolean(DAILY_DATA_UPDATE_KEY, false)

}