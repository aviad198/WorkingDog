package com.example.workingdog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.workingdog.databinding.GetToKnowFragmentBinding


class GetToKnowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: GetToKnowFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.get_to_know_fragment, container, false
        )
        binding.buttonDone.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_getToKnowFragment2_to_activityTrackerFragment) }
//Spinners
      val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
          this.requireContext(),
          R.array.jobs,
          android.R.layout.simple_spinner_item
      )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.jobSpinner.adapter = adapter

        return binding.root
    }
}