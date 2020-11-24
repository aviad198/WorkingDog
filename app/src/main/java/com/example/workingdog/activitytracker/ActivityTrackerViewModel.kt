package com.example.workingdog.activitytracker


import android.app.Application

import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.util.Log

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import com.example.workingdog.R
import com.example.workingdog.database.ActivityDatabaseDao
import com.example.workingdog.database.TimeTrack
import kotlinx.coroutines.launch
import java.util.*


/**
 * ViewModel for ActivityTrackerFragment.
 */
class ActivityTrackerViewModel(
    val database: ActivityDatabaseDao, application: Application
) : AndroidViewModel(application) {


    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        var COUNTDOWN_TIME = 60000L
    }
    private var startTracking = true



    private val timer: CountDownTimer

    var todayTimeA  = MutableLiveData<Double>()

    private var _buttonText =  MutableLiveData<String>()
    val buttonText : LiveData<String>
        get()=_buttonText




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
    private fun updateBtnText(){
        if(startTracking)

            _buttonText.value = "Start"
        else
            _buttonText.value = "Stop"
    }



    init {
        todayTimeA.value= 0.0
        initializeToday()
        updateBtnText()
/*
need to fix timer so it wont stop, try to find better implement for time
 */
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                //faster then a second for testing
                todayTimeA.value= todayTimeA.value?.plus((millisUntilFinished/50.0/60/60))
                Log.i("time", todayTimeA.value.toString())
                COUNTDOWN_TIME++
            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
            }
        }

    }

    private fun initializeToday() {
        viewModelScope.launch {
            today.value = getTodayFromDatabase()
            //_imagePics.value = R.drawable.letsdothis
            setTodayTimeVal()
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
        if (startTracking)
            onStartTracking()
        else
            onStopTracking()


    }





    private fun onStartTracking() {
        viewModelScope.launch {
            // Create a new day, which captures the current time,
            // and insert it into the database.

            startTracking = false
            updateBtnText()
            val newDay = TimeTrack()
            insert(newDay)
            today.value = getTodayFromDatabase()
            timer.start()
        }
    }


    /**
     * Executes when the STOP button is clicked.
     */
    private fun onStopTracking() {
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch(),
            // not the lambda.
            timer.cancel()
            startTracking = true
            updateBtnText()

            val oldDay = today.value ?: return@launch


            oldDay.endTimeMilli = Calendar.getInstance()

            update(oldDay)
            setTodayTimeVal()

        }
    }

    suspend fun setTodayTimeVal(){
        val todayStart = Calendar.getInstance()
        todayStart.set(Calendar.HOUR_OF_DAY, 6)
        val tomorrowStart = Calendar.getInstance()
        tomorrowStart.set(Calendar.DATE, tomorrowStart.get(Calendar.DATE + 1))
        tomorrowStart.set(Calendar.HOUR_OF_DAY, 6)

        if (database.getAllByDate(todayStart, tomorrowStart) != null ){
            todayTimeA.value = todayTimeA.value?.plus(database.getAllByDate(todayStart, tomorrowStart)!! /1000.0/60.0/60.0)
        }
        else
            todayTimeA.value = 0.0

    }





    /**
     * Executes when the CLEAR button is clicked.
     */
    fun onClear() {
        viewModelScope.launch {
            clear()
            todayTimeA.value= 0.0
            today.value = null
        }
    }




}

