package com.example.ejercicio2.ui.start

import com.example.ejercicio2.databinding.ActivityStartBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.ui.login.LoginActivity
import com.example.ejercicio2.ui.sing_up.SingUpActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    companion object {
        const val CLIENT = "client"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            goToLogin()
        }

        binding.btnSingUp.setOnClickListener {
            goToSingUp()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToSingUp() {
        val intent = Intent(this, SingUpActivity::class.java)
        startActivity(intent)
    }
}