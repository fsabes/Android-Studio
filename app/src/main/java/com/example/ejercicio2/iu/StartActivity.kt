package com.example.ejercicio2.iu

import com.example.ejercicio2.databinding.ActivityStartBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    companion object{
        val CLIENTE = "cliente"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
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
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navegarHaciaSingUp() {
        val intent = Intent(this, SingUpActivity::class.java)
        startActivity(intent)
    }
}