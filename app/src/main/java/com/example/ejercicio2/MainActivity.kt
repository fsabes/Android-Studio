package com.example.ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            navegarHaciaLogin()
        }

        binding.btnSingUp.setOnClickListener {
            navegarHaciaSingUp()
        }
    }

    private fun navegarHaciaLogin() {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }

    private fun navegarHaciaSingUp() {
        val intent = Intent(this,SingUp::class.java)
        startActivity(intent)
    }
}