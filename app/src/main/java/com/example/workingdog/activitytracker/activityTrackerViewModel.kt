package com.example.workingdog.activitytracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.workingdog.database.ActivityDatabaseDao
import com.example.workingdog.database.TimeTrack
import com.example.workingdog.formatDays

import kotlinx.coroutines.*

/**
 * ViewModel for ActivityTrackerFragment.
 */
class ActivityTrackerViewModel(
        val database: ActivityDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        var startTracking = true
        private var viewModelJob = Job()

        override fun onCleared() {
                super.onCleared()
                viewModelJob.cancel()
        }

        private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

        private var today = MutableLiveData<TimeTrack?>()

        private val days = database.getAllActivities()

        init {
                initializeTonight()
        }

        private fun initializeTonight() {
                uiScope.launch {
                        today.value = getTodayFromDatabase()
                }
        }
        private suspend fun getTodayFromDatabase():  TimeTrack? {
                return withContext(Dispatchers.IO) {
                        var today = database.getDay()

                        if (today?.endTimeMilli != today?.startTimeMilli) {
                                today = null
                        }
                        today
                }}

        fun startStopTracking(){
                if(startTracking)
                        onStartTracking()
                else
                        onStopTracking()
        }
        fun onStartTracking() {
                uiScope.launch {
                        val newDay = TimeTrack()
                        database.insert(newDay)
                        today.value = getTodayFromDatabase()
                }
                }
        private suspend fun insert(today: TimeTrack) {
                withContext(Dispatchers.IO) {
                        database.insert(today)
                }
        }
        fun onStopTracking() {
                uiScope.launch {
                        val oldDay = today.value ?: return@launch
                        oldDay.endTimeMilli = System.currentTimeMillis()
                        database.update(oldDay)
                }
        }

        private suspend fun update(night: TimeTrack) {
                withContext(Dispatchers.IO) {
                        database.update(night)
                }
        }

        fun onClear() {
                uiScope.launch {
                        clear()
                        today.value = null
                }
        }

        suspend fun clear() {
                withContext(Dispatchers.IO) {
                        database.clear()
                }
        }

        val daysString = Transformations.map(days) { days ->
                formatDays(days, application.resources)
        }

}

