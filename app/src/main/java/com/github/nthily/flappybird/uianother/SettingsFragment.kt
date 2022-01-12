package com.github.nthily.flappybird.uianother
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.nthily.flappybird.R
import kotlinx.android.synthetic.main.fragment_settings.*

//var count = 1

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_mainMenuFragment
            )
        }

        russianBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_mainMenuFragment
            )
        }

        if(count == 1){
            //Flag
//            language.visibility = View.VISIBLE
            //Language
//            russianLanguage.visibility = View.INVISIBLE
            soundText.visibility = View.VISIBLE
            //Sound
            russianSoundText.visibility = View.INVISIBLE
            vibration.visibility = View.VISIBLE
            //Vibration
            russianVibration.visibility = View.INVISIBLE
            back.visibility = View.VISIBLE
            //Back
            russianBack.visibility = View.INVISIBLE
        }else{
            //Flag
//            russianLanguage.visibility = View.VISIBLE
            //Language
//            language.visibility = View.INVISIBLE
            russianSoundText.visibility = View.VISIBLE
            //Sound
            soundText.visibility = View.INVISIBLE
            vibration.visibility = View.INVISIBLE
            //Vibration
            russianVibration.visibility = View.VISIBLE
            back.visibility = View.INVISIBLE
            russianBack.visibility = View.VISIBLE
        }

        soundTextOn.setOnClickListener {

            soundTextOn.setTextColor(Color.parseColor("#F8E64C"))
            soundTextOff.setTextColor(Color.parseColor("#33F8E64C"))
        }
        soundTextOff.setOnClickListener {

            soundTextOn.setTextColor(Color.parseColor("#33F8E64C"))
            soundTextOff.setTextColor(Color.parseColor("#F8E64C"))
        }

        vibrationTextOn.setOnClickListener {
            vibrationTextOn.setTextColor(Color.parseColor("#F8E64C"))
            vibrationTextOff.setTextColor(Color.parseColor("#33F8E64C"))
        }

        vibrationTextOff.setOnClickListener {

            vibrationTextOn.setTextColor(Color.parseColor("#33F8E64C"))
            vibrationTextOff.setTextColor(Color.parseColor("#F8E64C"))
        }
    }
}