package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.splashscreen.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var animation=AnimationUtils.loadAnimation(this,R.anim.app_splash)
        binding.splashimage.startAnimation(animation)

        Handler().postDelayed({
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        },5000)


    }
}