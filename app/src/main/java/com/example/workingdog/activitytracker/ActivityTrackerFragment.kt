package com.example.workingdog.activitytracker

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.workingdog.R
import com.example.workingdog.database.ActivityDatabase
import com.example.workingdog.databinding.ActivityMainBinding
import com.example.workingdog.databinding.FragmentActivityTrackerBinding
import kotlinx.android.synthetic.main.fragment_activity_tracker.*
import java.util.*


/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */

class ActivityTrackerFragment : Fragment() {
    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_activity_quality.
     */
    private lateinit var binding: FragmentActivityTrackerBinding

    private lateinit var viewModel: ActivityTrackerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_activity_tracker, container, false)

        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        val dataSource = ActivityDatabase.getInstance(application).activityDatabaseDao
        val viewModelFactory = ActivityTrackerViewModelFactory(dataSource, application)
        // Get a reference to the ViewModel associated with this fragment.

        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ActivityTrackerViewModel::class.java)



        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
     //   binding.activityTrackerViewModel = activityTrackerViewModel
        binding.TimeBtn.text = viewModel.buttonText
        binding.TimeBtn.setOnClickListener {
            viewModel.startStopTracking()
            viewModel.updateBtnText()
            updateBtnText()
        }
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateBtnText(){
        binding.TimeBtn.text = viewModel.buttonText
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

}