package com.example.ejercicio2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivityLoginBinding
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.ui.start.StartActivity
import com.example.ejercicio2.ui.home.HomeActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val db = Firebase.firestore

        binding.btnLogin.setOnClickListener {
            if (binding.userName.text.isNullOrBlank() || binding.password.text.isNullOrBlank()) {
                binding.tvMsjError.text = AppConstants.EMPTY_FIELDS
            } else {

                db.collection("users")
                    .whereEqualTo("username", binding.userName.text.toString())
                    .get()
                    .addOnSuccessListener { documents ->
                       if( documents.isEmpty){
                           binding.tvMsjError.text = AppConstants.USER_NOT_EXIST
                       }else{
                           val intent = Intent(this, HomeActivity::class.java)
                           val client = Client(binding.userName.text.toString())
                           intent.putExtra(StartActivity.CLIENT, client)
                           startActivity(intent)
                       }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Error getting documents: ", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}