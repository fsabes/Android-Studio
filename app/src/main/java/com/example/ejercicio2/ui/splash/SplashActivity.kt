package com.example.ejercicio2.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.ejercicio2.R
import com.example.ejercicio2.ui.start.StartActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, StartActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 2000)
    }
}