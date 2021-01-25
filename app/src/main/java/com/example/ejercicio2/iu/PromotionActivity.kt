package com.example.ejercicio2.iu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.RecipeAdapter
import com.example.ejercicio2.RecipeVerticalAdapter
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PromotionActivity : AppCompatActivity(), Callback<Recipes> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion)

        setSupportActionBar(findViewById(R.id.toolbar_promotion))
        supportActionBar?.title = "Promotion"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RecipeService::class.java)

        val call: Call<Recipes> = retrofit.getRecipes()
        call.enqueue(this)

    }

    override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
        if(response.isSuccessful)
        {
            var listRecipes: Recipes? = response.body()
            if (listRecipes != null) {
                Log.d("respuesta", "elementos : ${listRecipes.recipes.size}")

                val rv = findViewById<RecyclerView>(R.id.rv_list_recipe_vertical)

                val recipeVerticalAdapter = RecipeVerticalAdapter(listRecipes)
                rv.adapter = recipeVerticalAdapter
                rv.layoutManager = LinearLayoutManager(this@PromotionActivity, LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    override fun onFailure(call: Call<Recipes>, t: Throwable) {
        TODO("Not yet implemented")
    }
}