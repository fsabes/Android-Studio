package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityHomeBinding

class ActivityHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.ToolbarHome))
        supportActionBar?.title = "HOME"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.let {
            var cliente = intent.extras?.getParcelable<Cliente>("cliente")
            if (cliente != null) {
                binding.textView.text = cliente.saludar()
            }
        }
    }
}