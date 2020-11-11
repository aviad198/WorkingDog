package com.example.workingdog.activitytracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.workingdog.database.ActivityDatabaseDao

/**
 * ViewModel for ActivityTrackerFragment.
 */
class ActivityTrackerViewModel(
        val database: ActivityDatabaseDao,
        application: Application) : AndroidViewModel(application) {
}
