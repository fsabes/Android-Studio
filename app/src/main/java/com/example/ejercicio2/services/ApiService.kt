package com.example.ejercicio2.services

import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.entities.recipes.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("recipes/random?apiKey=${AppConstants.API_KEY}&number=10&tags=")
    fun getRecipes(): Call<Recipes>

    @GET("https://api.spoonacular.com/recipes/{id}/information?apiKey=${AppConstants.API_KEY}&includeNutrition=false")
    fun getRecipeDetails(@Path("id") id: Int): Call<Recipe>
}