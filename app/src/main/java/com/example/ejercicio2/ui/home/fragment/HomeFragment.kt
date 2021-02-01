package com.example.ejercicio2.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.adapter.RecipeAdapter
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.services.ApiClient
import com.example.ejercicio2.ui.Promotion.PromotionActivity
import com.example.ejercicio2.ui.start.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv = view.findViewById<TextView>(R.id.tv_btn_ver_mas)
        val rv = view.findViewById<RecyclerView>(R.id.rv_list_recipe)
        val tvHello = view.findViewById<TextView>(R.id.tv_hello)

        tv.setOnClickListener {
            goToPromotionActivity()
        }
        getRecipes(rv)

        tvHello.text = "Hola, ${
            activity?.intent?.getParcelableExtra<Client>(StartActivity.CLIENT)?.getName()
                ?.capitalize()
        }"
    }

    private fun getRecipes(rv: RecyclerView) {
        ApiClient.getServiceClient().getRecipes()
            .enqueue(object : Callback<Recipes> {
                override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                RecipeAdapter.setRecipeAdapter(
                                    this@HomeFragment.requireContext(),
                                    rv,
                                    it
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Recipes>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun goToPromotionActivity() {
        val intent = Intent(view?.context, PromotionActivity::class.java)
        startActivity(intent)
    }
}