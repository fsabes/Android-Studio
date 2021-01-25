package com.example.ejercicio2.iu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.RecipeAdapter
import com.example.ejercicio2.databinding.ActivityHomeBinding
import com.example.ejercicio2.entities.Cliente
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.RecipeService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity(), Callback<Recipes> {
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

        //retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RecipeService::class.java)

        val call: Call<Recipes> = retrofit.getRecipes()

        call.enqueue(this)

        binding.tvBtnVerMas.setOnClickListener {
            goToPromotionActivity()
        }

        intent.let {
            var cliente = intent.extras?.getParcelable<Cliente>(StartActivity.CLIENTE)
            if (cliente != null) {
                binding.tvSaludo.text = cliente.saludar()
            }
        }
    }

    override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
        if(response.isSuccessful)
        {
            var listRecipes: Recipes? = response.body()
            if (listRecipes != null) {
                Log.d("respuesta", "elementos : ${listRecipes.recipes.size}")

                val rv = findViewById<RecyclerView>(R.id.rv_list_recipe)

                val recipeAdapter = RecipeAdapter(listRecipes)
                rv.adapter = recipeAdapter
                rv.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

    override fun onFailure(call: Call<Recipes>, t: Throwable) {
        TODO("Not yet implemented")
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.users -> {
//                binding.textView.text = SharedPreferencesManeger.listar(this@ActivityHome)
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

