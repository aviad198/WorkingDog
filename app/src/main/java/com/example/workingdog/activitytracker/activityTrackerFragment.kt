package com.example.workingdog.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workingdog.R
import com.example.workingdog.database.ActivityDatabase
import com.example.workingdog.databinding.ActivityMainBinding

//import androidx.databinding.DataBindingUtil
/**
 * A fragment with buttons to record start and end times for activity, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class ActivityTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_activity_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: ActivityMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.activity_main, container, false)

        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        val dataSource = ActivityDatabase.getInstance(application).activityDatabaseDao
        val viewModelFactory = ActivityTrackerViewModelFactory(dataSource, application)
        // Get a reference to the ViewModel associated with this fragment.
        val activityTrackerViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(ActivityTrackerViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.activityTrackerViewModel = activityTrackerViewModel

        binding.lifecycleOwner= this
        return binding.root
    }
}
