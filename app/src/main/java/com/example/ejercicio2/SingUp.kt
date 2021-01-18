package com.example.ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivitySingUpBinding

class SingUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySingUpBinding = ActivitySingUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSingUp.isEnabled = false

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

                val intent = Intent(this,Home::class.java)
                intent.putExtra("cliente",cliente)
                startActivity(intent)
            }
            else{
                binding.textViewCamposVacios.text = "empty fields are not allowed"
            }
        }
    }
}