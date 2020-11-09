package com.example.workingdog.activitytracker.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workingdog.R
import com.example.workingdog.activitytracker.database.ActivityDatabase
import com.google.android.material.snackbar.Snackbar
import androidx.databinding.DataBindingUtil
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
        val binding: FragmentActivityMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.activity_main, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ActivityDatabase.getInstance(application).activityDatabaseDao

        val viewModelFactory = ActivityTrackerViewModelFactory(dataSource, application)

        val activityTrackerViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(ActivityTrackerViewModel::class.java)

        binding.activityTrackerViewModel = activityTrackerViewModel

        // binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this



        // Add an Observer on the state variable for showing a Snackbar message
        // when the CLEAR button is pressed.
        activityTrackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        getString(R.string.cleared_message),
                        Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the snackbar is only shown once, even if the device
                // has a configuration change.
                activityTrackerViewModel.doneShowingSnackbar()
            }
        })

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        activityTrackerViewModel.navigateToActivityQuality.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                // We need to get the navController from this, because button is not ready, and it
                // just has to be a view. For some reason, this only matters if we hit stop again
                // after using the back button, not if we hit stop and choose a quality.
                // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
                // popping the stack to get the correct behavior if we press stop multiple times
                // followed by back.
                // Also: https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
                this.findNavController().navigate(
                        ActivityTrackerFragmentDirections
                                .actionActivityTrackerFragmentToActivityQualityFragment(night.nightId))
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                activityTrackerViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
