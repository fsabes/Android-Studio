package com.example.ejercicio2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.databinding.ActivitySingUpBinding
import com.example.ejercicio2.entities.Cliente

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingUp.isEnabled = false
        //toolbar
        setSupportActionBar(findViewById(R.id.myToolbar))
        supportActionBar?.title = "SING UP"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSingUp.isEnabled = isChecked
        }

        binding.btnSingUp.setOnClickListener {
            if(binding.name.text.isNotEmpty() && binding.surName.text.isNotEmpty() && binding.email.text.isNotEmpty() &&
                    binding.password.text.isNotEmpty() && binding.userName.text.isNotEmpty() && binding.confirmPassword.text.isNotEmpty()) {
                val cliente = Cliente(
                        binding.name.text.toString(),
                        binding.surName.text.toString(),
                        binding.userName.text.toString(),
                        binding.email.text.toString(),
                        binding.password.text.toString())
                pasarDatos(cliente)
            }
            else{
                binding.textViewCamposVacios.text = "empty fields are not allowed"
            }
        }
    }

    private fun pasarDatos(cliente : Cliente){
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(StartActivity.CLIENTE,cliente)
            startActivity(intent)
    }
}