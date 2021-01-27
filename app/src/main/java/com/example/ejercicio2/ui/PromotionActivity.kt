package com.example.ejercicio2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.RecipeVerticalAdapter
import com.example.ejercicio2.databinding.ActivityPromotionBinding
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromotionActivity : AppCompatActivity() , RecipeVerticalAdapter.OnClickRecipe{

    companion object{
        const val ID = "id"
    }

    private lateinit var binding: ActivityPromotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPromotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar_promotion))
        supportActionBar?.title = "Promociones del dia"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getRecipes(binding.rvListRecipeVertical)
    }

    private fun getRecipes(rv: RecyclerView) {
        ApiClient.getServiceClient().getRecipes()
                .enqueue(object : Callback<Recipes>, RecipeVerticalAdapter.OnClickRecipe {
                    override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                        if (response.isSuccessful) {
                            response.body().let {
                                if (it != null) {
                                    RecipeVerticalAdapter.setRecipeVerticalAdapter(this@PromotionActivity,rv,it,this)
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<Recipes>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onClickRecipe(id: Int) {
                        val i = Intent(this@PromotionActivity, DetailsActivity::class.java)
                        i.putExtra(ID,id)
                        startActivity(i)
                    }
                })
    }



    override fun onClickRecipe(id: Int) {
        val i = Intent(this@PromotionActivity, DetailsActivity::class.java)
        i.putExtra(ID,id)
        startActivity(i)
    }

}