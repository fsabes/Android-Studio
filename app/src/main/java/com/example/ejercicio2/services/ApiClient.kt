package com.example.ejercicio2.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://api.spoonacular.com/"

    private val mRetrofit : Retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private var mInterface : ApiService = mRetrofit.create(ApiService::class.java)

    fun getServiceClient() = mInterface

}