package com.example.ejercicio2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.RecipeAdapter
import com.example.ejercicio2.databinding.ActivityHomeBinding
import com.example.ejercicio2.entities.Cliente
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.ApiClient
import retrofit2.*


class HomeActivity : AppCompatActivity(){
    //binding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        setSupportActionBar(findViewById(R.id.ToolbarHome))
        supportActionBar?.title = "HOME"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //ver mas
        binding.tvBtnVerMas.setOnClickListener {
            goToPromotionActivity()
        }

        //retrofit
        getRecipes(binding.rvListRecipe)
        //gotoActivity
        intent.let {
            var cliente = intent.extras?.getParcelable<Cliente>(StartActivity.CLIENTE)
            if (cliente != null) {
                binding.tvSaludo.text = cliente.saludar()
            }
        }
    }

    private fun getRecipes(rv: RecyclerView) {
        ApiClient.getServiceClient().getRecipes()
                .enqueue(object : Callback<Recipes> {
                    override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                        if (response.isSuccessful) {
                            response.body().let {
                                if (it != null) {
                                    RecipeAdapter.setRecipeAdapter(this@HomeActivity,rv,it)
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<Recipes>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id. -> {
//
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun goToPromotionActivity() {
        val intent = Intent(this, PromotionActivity::class.java)
        startActivity(intent)
    }
}

