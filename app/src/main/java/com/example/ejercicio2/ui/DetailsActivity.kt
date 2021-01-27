package com.example.ejercicio2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.R
import com.example.ejercicio2.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar_details))
        supportActionBar?.title = "Detalles"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.let {
            var id = intent.extras?.getInt(PromotionActivity.ID)
            binding.tvPromotionTitle.text = id.toString()
        }

    }
}