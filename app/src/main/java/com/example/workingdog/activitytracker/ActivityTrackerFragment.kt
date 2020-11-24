package com.example.workingdog.activitytracker

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.*
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workingdog.R
import com.example.workingdog.database.ActivityDatabase
import com.example.workingdog.databinding.FragmentActivityTrackerBinding
import kotlin.math.roundToInt


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
    //Buzzers
    private val FIRST_BUZZ_PATTERN = longArrayOf(0, 100)
    private val SECOND_BUZZ_PATTERN = longArrayOf(0, 200)
    private val THIRD_BUZZ_PATTERN = longArrayOf(0, 100,200)
    private val FOURTH_BUZZ_PATTERN = longArrayOf(0,2000)

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

        //Add some buzzing
        viewModel.todayTimeA.observe(viewLifecycleOwner, Observer { newTime ->
             if (newTime!= null){

             binding.dayProgress.progress = newTime.toInt()
                 binding.todayTime.text = ((newTime * 10.0).roundToInt() / 10.0).toString()
                 if (newTime < 4)
                     binding.DogStatus.setImageResource(R.drawable.letsdothis)
                 if (newTime > 4){
                     binding.DogStatus.setImageResource(R.drawable.stillworking)
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                         activity?.getSystemService<Vibrator>()
                             ?.vibrate(VibrationEffect.createWaveform(FIRST_BUZZ_PATTERN, -1))
                     } }
                 if (newTime > 6){
                     binding.DogStatus.setImageResource(R.drawable.timetorest)
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                     activity?.getSystemService<Vibrator>()
                         ?.vibrate(VibrationEffect.createWaveform(SECOND_BUZZ_PATTERN, -1))
                 } }
                 if (newTime > 8){
                     binding.DogStatus.setImageResource(R.drawable.letsstrech)
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                     activity?.getSystemService<Vibrator>()
                         ?.vibrate(VibrationEffect.createWaveform(THIRD_BUZZ_PATTERN, -1))
                 } }
                 if (newTime > 10){
                     binding.DogStatus.setImageResource(R.drawable.workighard)
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                     activity?.getSystemService<Vibrator>()
                         ?.vibrate(VibrationEffect.createWaveform(FOURTH_BUZZ_PATTERN, -1))
                 } }
             }

    })
        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
     //   binding.activityTrackerViewModel = activityTrackerViewModel





        binding.lifecycleOwner = this

        binding.activityTrackerViewModel = viewModel
        return binding.root
    }

}