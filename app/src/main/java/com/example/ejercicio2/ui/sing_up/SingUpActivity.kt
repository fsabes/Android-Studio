package com.example.ejercicio2.ui.sing_up

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivitySingUpBinding
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.ui.home.HomeActivity
import com.example.ejercicio2.ui.start.StartActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirestoreRegistrar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //firabase
        val db = Firebase.firestore

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
                binding.tvEmptyFields.text = AppConstants.EMPTY_FIELDS
            } else {
                val client = Client(
                    binding.name.text.toString(),
                    binding.surName.text.toString(),
                    binding.userName.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )

                db.collection("users")
                    .add(client.convertToHashMap())
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "DocumentSnapshot added with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener { e ->
                        Toast.makeText( this,"Error adding document $e", Toast.LENGTH_SHORT).show()
                    }
                sendData(client)
            }
        }
    }

    private fun sendData(client: Client) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(StartActivity.CLIENT, client)
        startActivity(intent)
    }
}