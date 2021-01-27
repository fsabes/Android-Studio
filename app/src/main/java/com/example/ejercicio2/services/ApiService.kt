package com.example.ejercicio2.services

import com.example.ejercicio2.entities.recipes.Recipes
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("recipes/random?apiKey=15effca188b144f0b5495f32e6aad8ea&number=10&tags=")
    fun getRecipes() : Call<Recipes>
}