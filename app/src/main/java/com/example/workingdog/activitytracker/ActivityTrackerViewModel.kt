package com.example.workingdog.activitytracker


import android.app.Application
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope

import com.example.workingdog.R
import com.example.workingdog.database.ActivityDatabaseDao
import com.example.workingdog.database.TimeTrack
import kotlinx.android.synthetic.main.fragment_activity_tracker.view.*

import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*


/**
 * ViewModel for ActivityTrackerFragment.
 */
class ActivityTrackerViewModel(
    val database: ActivityDatabaseDao, application: Application) : AndroidViewModel(application) {

    var startTracking = true
    var todayTimeA = 0.0



    private var today = MutableLiveData<TimeTrack?>()

    private val days = database.getAllActivities()


    /**
     * Converted days to Spanned for displaying.
     */
/*    val daysString = Transformations.map(days) { days ->
        formatDays(days, application.resources)
    }*/

//        val dayTime = Transformations.map(days) { days ->
//        updateDay(days)
//    }


    init {
        initializeToday()
    }

    private fun initializeToday() {
        viewModelScope.launch {
            today.value = getTodayFromDatabase()
        }
    }

    /**
     *  Handling the case of the stopped app or forgotten recording,
     *  the start and end times will be the same.j
     *
     *  If the start time and end time are not the same, then we do not have an unfinished
     *  recording.
     */
    private suspend fun getTodayFromDatabase(): TimeTrack? {
            var today = database.getToday()
            if (today?.endTimeMilli != today?.startTimeMilli) {
                today = null
            }
            return today
        }

    private suspend fun clear() {
        database.clear()
    }

    private suspend fun insert(day: TimeTrack) {
        database.insert(day)
    }

    private suspend fun update(day: TimeTrack) {
        database.update(day)
    }

    /**
     * Executes when the START button is clicked.
     */
    fun startStopTracking(){
        if (startTracking) {

            onStartTracking()

        }
        else{
            onStopTracking()
            startTracking = true }

      //  Log.i("Day time is:", ""+dayTime)
    }



    fun onStartTracking() {
        viewModelScope.launch {
            // Create a new day, which captures the current time,
            // and insert it into the database.

            startTracking = false
            val newDay = TimeTrack()
            insert(newDay)
            today.value = getTodayFromDatabase()
        }
    }


    /**
     * Executes when the STOP button is clicked.
     */
    fun onStopTracking() {
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch(),
            // not the lambda.
            val oldDay = today.value ?: return@launch

            // Update the day in the database to add the end time.
            oldDay.endTimeMilli = Calendar.getInstance()

            val todayStart = Calendar.getInstance()
            todayStart.set(Calendar.HOUR_OF_DAY, 6)
            val tomorrowStart = Calendar.getInstance()
            tomorrowStart.set(Calendar.DATE, tomorrowStart.get(Calendar.DATE + 1))
            tomorrowStart.set(Calendar.HOUR_OF_DAY, 6)

            update(oldDay)

            todayTimeA = database.getAllByDate(todayStart, tomorrowStart)!!
            println(todayTimeA / 1000 / 60 / 60)

        }
    }
    var dogstat = R.drawable.stillworking
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {

        view.setImageDrawable(drawable)
    }

    /**
     * Executes when the CLEAR button is clicked.
     */
    fun onClear() {
        viewModelScope.launch {
            clear()
            today.value = null
        }
    }




}

