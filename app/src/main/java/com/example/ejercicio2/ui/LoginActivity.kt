package com.example.ejercicio2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.databinding.ActivityLoginBinding
import com.example.ejercicio2.entities.Cliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(findViewById(R.id.toolbarLogin))
        supportActionBar?.title = "LOGIN"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnLogin.setOnClickListener {
            if(binding.userName.text.isEmpty() && binding.password.text.isEmpty()){
                binding.textViewCamposVacios.text = "empty fields are not allowed"
            }
            else{
                val intent = Intent(this, HomeActivity::class.java)
                val cliente = Cliente("franco","sabes",binding.userName.text.toString(),"adsa@gmail.com","asasdasdasdd")
                intent.putExtra("cliente",cliente)
                startActivity(intent)
            }
        }
    }
}