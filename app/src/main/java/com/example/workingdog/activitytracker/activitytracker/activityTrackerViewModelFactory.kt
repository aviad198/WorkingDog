//package com.example.workingdog.activitytracker.activitytracker
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.workingdog.activitytracker.database.ActivityDatabaseDao
//
///**
// * This is pretty much boiler plate code for a ViewModel Factory.
// *
// * Provides the ActivityDatabaseDao and context to the ViewModel.
// */
//class ActivityTrackerViewModelFactory(
//        private val dataSource: ActivityDatabaseDao,
//        private val application: Application) : ViewModelProvider.Factory {
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ActivityTrackerViewModel::class.java)) {
//            return ActivityTrackerViewModel(dataSource, application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
