package com.example.ejercicio2.ui.sing_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivitySingUpBinding
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.ui.start.StartActivity
import com.example.ejercicio2.ui.home.HomeActivity

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingUp.isEnabled = false
        //toolbar
        setSupportActionBar(findViewById(R.id.toolbar_sing_up))
        supportActionBar?.title = AppConstants.SING_UP
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSingUp.isEnabled = isChecked
        }

        binding.btnSingUp.setOnClickListener {
            if (binding.name.text.isNullOrBlank() || binding.surName.text.isNullOrBlank() || binding.email.text.isNullOrBlank() ||
                binding.password.text.isNullOrBlank() || binding.userName.text.isNullOrBlank() || binding.confirmPassword.text.isNullOrBlank()
            ) {
                val client = Client(
                    binding.name.text.toString(),
                    binding.surName.text.toString(),
                    binding.userName.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                sendData(client)
            } else {
                binding.tvEmptyFields.text = AppConstants.EMPTY_FIELDS
            }
        }
    }

    private fun sendData(client: Client) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(StartActivity.CLIENT, client)
        startActivity(intent)
    }
}