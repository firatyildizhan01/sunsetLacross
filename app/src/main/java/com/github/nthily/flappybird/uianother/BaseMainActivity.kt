package com.github.nthily.flappybird.uianother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment

import android.media.MediaPlayer
import com.github.nthily.flappybird.R

class baseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_main)




        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.

        actionBar?.hide()

        val music: MediaPlayer = MediaPlayer.create(this, R.raw.menugamesong)
        music.start()
        
        val navHostFragment = supportFragmentManager.findFragmentById(com.github.nthily.flappybird.R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


    }
}
