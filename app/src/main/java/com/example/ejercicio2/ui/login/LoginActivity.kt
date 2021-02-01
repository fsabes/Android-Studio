package com.example.ejercicio2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivityLoginBinding
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.ui.start.StartActivity
import com.example.ejercicio2.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(findViewById(R.id.toolbar_Login))
        supportActionBar?.title = AppConstants.LOGIN
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnLogin.setOnClickListener {
            if (binding.userName.text.isNullOrBlank() || binding.password.text.isNullOrBlank()) {
                binding.tvEmptyFields.text = AppConstants.EMPTY_FIELDS
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                val client = Client(binding.userName.text.toString())
                intent.putExtra(StartActivity.CLIENT, client)
                startActivity(intent)
            }
        }
    }
}