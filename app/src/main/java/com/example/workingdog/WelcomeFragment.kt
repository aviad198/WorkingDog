package com.example.workingdog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.workingdog.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

            val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_welcome, container, false)


            //Animation
            val topAnim : Animation = AnimationUtils.loadAnimation(this.context, R.anim.top_animation)
            val bottomAnim : Animation= AnimationUtils.loadAnimation(this.context, R.anim.bottom_animation)
            //hooks
            //hooks
            binding.imageLetsDoThis.animation = bottomAnim
            binding.titleWelcome.animation = topAnim
            binding.summaryApp.animation = topAnim


//            image = findViewById<ImageView>(R.id.imageLetsDoThis)
//            titel = findViewById<TextView>(R.id.titleWelcome)
//            subtitle = findViewById<TextView>(R.id.summaryApp)
//
//            image.setAnimation(bottomAnim)
//            titel.setAnimation(topAnim)
//            subtitle.setAnimation(topAnim)
//
//            button = findViewById<Button>(R.id.buttonLetsGetStarted)
//            button.setOnClickListener(View.OnClickListener { v -> openGetToKNow(v) })
            binding.buttonLetsGetStarted.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_welcomeFragment_to_getToKnowFragment2)}

            binding.lifecycleOwner = this


            return binding.root
        }
}
