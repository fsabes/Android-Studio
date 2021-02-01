package com.example.ejercicio2.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.databinding.ActivityDetailsBinding
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.services.ApiClient
import com.example.ejercicio2.utils.addBulletPoint
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var id: Int?
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar_details))
        supportActionBar?.title = AppConstants.DETAILS
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.let {
            id = intent.extras?.getInt(AppConstants.ID)
        }

        id?.let { getDetails(it) }
    }

    private fun getDetails(id: Int) {
        ApiClient.getServiceClient().getRecipeDetails(id)
            .enqueue(object : Callback<Recipe> {
                override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                Picasso.get()
                                    .load(it.image)
                                    .into(binding.ivDetails)
                                binding.tvDetailsTitle.text = it.title
                                binding.tvDetailsPrice.text = "$${it.pricePerServing}"
                                if (it.glutenFree) {
                                    binding.tvDetailsGlutenFree.text = AppConstants.GLUTEN_FREE
                                    binding.ivDetailsStart.visibility = View.VISIBLE
                                }
                                binding.tvDetailsSubtitle.text = it.summary
                                binding.tvPoints.text = it.spoonacularScore.toString()
                                binding.tvDetailsGlutenFree
                                binding.tvDetailsIngredients.movementMethod =
                                    ScrollingMovementMethod()
                                for (item in it.extendedIngredients) {
                                    binding.tvDetailsIngredients.append(item.originalString.addBulletPoint())
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Recipe>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}