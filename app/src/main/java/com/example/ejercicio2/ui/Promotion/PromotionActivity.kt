package com.example.ejercicio2.ui.Promotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.adapter.RecipeVerticalAdapter
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivityPromotionBinding
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.ApiClient
import com.example.ejercicio2.ui.details.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromotionActivity : AppCompatActivity(), RecipeVerticalAdapter.OnClickRecipe {

    private lateinit var binding: ActivityPromotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPromotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar_promotion))
        supportActionBar?.title = AppConstants.PROMOTION
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
                                RecipeVerticalAdapter.setRecipeVerticalAdapter(
                                    this@PromotionActivity,
                                    rv,
                                    it,
                                    this
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Recipes>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onClickRecipe(id: Int) {
                    val i = Intent(this@PromotionActivity, DetailsActivity::class.java)
                    i.putExtra(AppConstants.ID, id)
                    startActivity(i)
                }
            })
    }

    override fun onClickRecipe(id: Int) {
        val i = Intent(this@PromotionActivity, DetailsActivity::class.java)
        i.putExtra(AppConstants.ID, id)
        startActivity(i)
    }

}